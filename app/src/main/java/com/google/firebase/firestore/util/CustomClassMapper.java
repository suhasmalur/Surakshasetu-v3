package com.google.firebase.firestore.util;

import android.net.Uri;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.PropertyName;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.firestore.ThrowOnExtraProperties;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes10.dex */
public class CustomClassMapper {
    private static final int MAX_DEPTH = 500;
    private static final ConcurrentMap<Class<?>, BeanMapper<?>> mappers = new ConcurrentHashMap();

    private static void hardAssert(boolean assertion) {
        hardAssert(assertion, "Internal inconsistency");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void hardAssert(boolean assertion, String message) {
        if (!assertion) {
            throw new RuntimeException("Hard assert failed: " + message);
        }
    }

    public static Object convertToPlainJavaTypes(Object object) {
        return serialize(object);
    }

    public static Map<String, Object> convertToPlainJavaTypes(Map<?, Object> update) {
        Object converted = serialize(update);
        hardAssert(converted instanceof Map);
        Map<String, Object> convertedMap = (Map) converted;
        return convertedMap;
    }

    public static <T> T convertToCustomClass(Object obj, Class<T> cls, DocumentReference documentReference) {
        return (T) deserializeToClass(obj, cls, new DeserializeContext(ErrorPath.EMPTY, documentReference));
    }

    private static <T> Object serialize(T o) {
        return serialize(o, ErrorPath.EMPTY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Object serialize(T t, ErrorPath path) {
        if (path.getLength() > 500) {
            throw serializeError(path, "Exceeded maximum depth of 500, which likely indicates there's an object cycle");
        }
        if (t == 0) {
            return null;
        }
        if (t instanceof Number) {
            if ((t instanceof Long) || (t instanceof Integer) || (t instanceof Double) || (t instanceof Float)) {
                return t;
            }
            throw serializeError(path, String.format("Numbers of type %s are not supported, please use an int, long, float or double", t.getClass().getSimpleName()));
        }
        if ((t instanceof String) || (t instanceof Boolean)) {
            return t;
        }
        if (t instanceof Character) {
            throw serializeError(path, "Characters are not supported, please use Strings");
        }
        if (t instanceof Map) {
            Map<String, Object> result = new HashMap<>();
            for (Map.Entry<Object, Object> entry : ((Map) t).entrySet()) {
                Object key = entry.getKey();
                if (key instanceof String) {
                    String keyString = (String) key;
                    result.put(keyString, serialize(entry.getValue(), path.child(keyString)));
                } else {
                    throw serializeError(path, "Maps with non-string keys are not supported");
                }
            }
            return result;
        }
        if (t instanceof Collection) {
            if (t instanceof List) {
                List<Object> list = (List) t;
                List<Object> result2 = new ArrayList<>(list.size());
                for (int i = 0; i < list.size(); i++) {
                    result2.add(serialize(list.get(i), path.child("[" + i + "]")));
                }
                return result2;
            }
            throw serializeError(path, "Serializing Collections is not supported, please use Lists instead");
        }
        if (t.getClass().isArray()) {
            throw serializeError(path, "Serializing Arrays is not supported, please use Lists instead");
        }
        if (t instanceof Enum) {
            String enumName = ((Enum) t).name();
            try {
                Field enumField = t.getClass().getField(enumName);
                return BeanMapper.propertyName(enumField);
            } catch (NoSuchFieldException e) {
                return enumName;
            }
        }
        if ((t instanceof Date) || (t instanceof Timestamp) || (t instanceof GeoPoint) || (t instanceof Blob) || (t instanceof DocumentReference) || (t instanceof FieldValue)) {
            return t;
        }
        if ((t instanceof Uri) || (t instanceof URI) || (t instanceof URL)) {
            return t.toString();
        }
        BeanMapper<T> mapper = loadOrCreateBeanMapperForClass(t.getClass());
        return mapper.serialize(t, path);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T deserializeToType(Object obj, Type type, DeserializeContext deserializeContext) {
        if (obj == null) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            return (T) deserializeToParameterizedType(obj, (ParameterizedType) type, deserializeContext);
        }
        if (type instanceof Class) {
            return (T) deserializeToClass(obj, (Class) type, deserializeContext);
        }
        if (type instanceof WildcardType) {
            if (((WildcardType) type).getLowerBounds().length > 0) {
                throw deserializeError(deserializeContext.errorPath, "Generic lower-bounded wildcard types are not supported");
            }
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            hardAssert(upperBounds.length > 0, "Unexpected type bounds on wildcard " + type);
            return (T) deserializeToType(obj, upperBounds[0], deserializeContext);
        }
        if (type instanceof TypeVariable) {
            Type[] bounds = ((TypeVariable) type).getBounds();
            hardAssert(bounds.length > 0, "Unexpected type bounds on type variable " + type);
            return (T) deserializeToType(obj, bounds[0], deserializeContext);
        }
        if (type instanceof GenericArrayType) {
            throw deserializeError(deserializeContext.errorPath, "Generic Arrays are not supported, please use Lists instead");
        }
        throw deserializeError(deserializeContext.errorPath, "Unknown type encountered: " + type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> T deserializeToClass(Object obj, Class<T> cls, DeserializeContext deserializeContext) {
        if (obj == 0) {
            return null;
        }
        if (cls.isPrimitive() || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls) || Character.class.isAssignableFrom(cls)) {
            return (T) deserializeToPrimitive(obj, cls, deserializeContext);
        }
        if (String.class.isAssignableFrom(cls)) {
            return (T) convertString(obj, deserializeContext);
        }
        if (Date.class.isAssignableFrom(cls)) {
            return (T) convertDate(obj, deserializeContext);
        }
        if (Timestamp.class.isAssignableFrom(cls)) {
            return (T) convertTimestamp(obj, deserializeContext);
        }
        if (Blob.class.isAssignableFrom(cls)) {
            return (T) convertBlob(obj, deserializeContext);
        }
        if (GeoPoint.class.isAssignableFrom(cls)) {
            return (T) convertGeoPoint(obj, deserializeContext);
        }
        if (DocumentReference.class.isAssignableFrom(cls)) {
            return (T) convertDocumentReference(obj, deserializeContext);
        }
        if (cls.isArray()) {
            throw deserializeError(deserializeContext.errorPath, "Converting to Arrays is not supported, please use Lists instead");
        }
        if (cls.getTypeParameters().length > 0) {
            throw deserializeError(deserializeContext.errorPath, "Class " + cls.getName() + " has generic type parameters");
        }
        if (cls.equals(Object.class)) {
            return obj;
        }
        if (cls.isEnum()) {
            return (T) deserializeToEnum(obj, cls, deserializeContext);
        }
        return (T) convertBean(obj, cls, deserializeContext);
    }

    /* JADX WARN: Type inference failed for: r3v11, types: [T, java.util.ArrayList, java.util.List] */
    /* JADX WARN: Type inference failed for: r4v6, types: [T, java.util.HashMap] */
    /* JADX WARN: Type inference failed for: r6v4, types: [T, java.lang.Object] */
    private static <T> T deserializeToParameterizedType(Object obj, ParameterizedType parameterizedType, DeserializeContext deserializeContext) {
        Class cls = (Class) parameterizedType.getRawType();
        if (List.class.isAssignableFrom(cls)) {
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof List) {
                List list = (List) obj;
                ?? r3 = (T) new ArrayList(list.size());
                for (int i = 0; i < list.size(); i++) {
                    r3.add(deserializeToType(list.get(i), type, deserializeContext.newInstanceWithErrorPath(deserializeContext.errorPath.child("[" + i + "]"))));
                }
                return r3;
            }
            throw deserializeError(deserializeContext.errorPath, "Expected a List, but got a " + obj.getClass());
        }
        if (Map.class.isAssignableFrom(cls)) {
            Type type2 = parameterizedType.getActualTypeArguments()[0];
            Type type3 = parameterizedType.getActualTypeArguments()[1];
            if (!type2.equals(String.class)) {
                throw deserializeError(deserializeContext.errorPath, "Only Maps with string keys are supported, but found Map with key type " + type2);
            }
            Map<String, Object> mapExpectMap = expectMap(obj, deserializeContext);
            ?? r4 = (T) new HashMap();
            for (Map.Entry<String, Object> entry : mapExpectMap.entrySet()) {
                r4.put(entry.getKey(), deserializeToType(entry.getValue(), type3, deserializeContext.newInstanceWithErrorPath(deserializeContext.errorPath.child(entry.getKey()))));
            }
            return r4;
        }
        if (Collection.class.isAssignableFrom(cls)) {
            throw deserializeError(deserializeContext.errorPath, "Collections are not supported, please use Lists instead");
        }
        Map<String, Object> mapExpectMap2 = expectMap(obj, deserializeContext);
        BeanMapper beanMapperLoadOrCreateBeanMapperForClass = loadOrCreateBeanMapperForClass(cls);
        HashMap map = new HashMap();
        TypeVariable<Class<T>>[] typeParameters = beanMapperLoadOrCreateBeanMapperForClass.clazz.getTypeParameters();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments.length != typeParameters.length) {
            throw new IllegalStateException("Mismatched lengths for type variables and actual types");
        }
        for (int i2 = 0; i2 < typeParameters.length; i2++) {
            map.put(typeParameters[i2], actualTypeArguments[i2]);
        }
        return beanMapperLoadOrCreateBeanMapperForClass.deserialize(mapExpectMap2, map, deserializeContext);
    }

    private static <T> T deserializeToPrimitive(Object obj, Class<T> cls, DeserializeContext deserializeContext) {
        if (Integer.class.isAssignableFrom(cls) || Integer.TYPE.isAssignableFrom(cls)) {
            return (T) convertInteger(obj, deserializeContext);
        }
        if (Boolean.class.isAssignableFrom(cls) || Boolean.TYPE.isAssignableFrom(cls)) {
            return (T) convertBoolean(obj, deserializeContext);
        }
        if (Double.class.isAssignableFrom(cls) || Double.TYPE.isAssignableFrom(cls)) {
            return (T) convertDouble(obj, deserializeContext);
        }
        if (Long.class.isAssignableFrom(cls) || Long.TYPE.isAssignableFrom(cls)) {
            return (T) convertLong(obj, deserializeContext);
        }
        if (Float.class.isAssignableFrom(cls) || Float.TYPE.isAssignableFrom(cls)) {
            return (T) Float.valueOf(convertDouble(obj, deserializeContext).floatValue());
        }
        throw deserializeError(deserializeContext.errorPath, String.format("Deserializing values to %s is not supported", cls.getSimpleName()));
    }

    private static <T> T deserializeToEnum(Object obj, Class<T> cls, DeserializeContext deserializeContext) {
        if (obj instanceof String) {
            String name = (String) obj;
            Field[] fields = cls.getFields();
            int length = fields.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    Field field = fields[i];
                    if (!field.isEnumConstant() || !name.equals(BeanMapper.propertyName(field))) {
                        i++;
                    } else {
                        name = field.getName();
                        break;
                    }
                }
            }
            try {
                return (T) Enum.valueOf(cls, name);
            } catch (IllegalArgumentException e) {
                throw deserializeError(deserializeContext.errorPath, "Could not find enum value of " + cls.getName() + " for value \"" + name + "\"");
            }
        }
        throw deserializeError(deserializeContext.errorPath, "Expected a String while deserializing to enum " + cls + " but got a " + obj.getClass());
    }

    private static <T> BeanMapper<T> loadOrCreateBeanMapperForClass(Class<T> clazz) {
        BeanMapper<T> mapper = (BeanMapper) mappers.get(clazz);
        if (mapper == null) {
            BeanMapper<T> mapper2 = new BeanMapper<>(clazz);
            mappers.put(clazz, mapper2);
            return mapper2;
        }
        return mapper;
    }

    private static Map<String, Object> expectMap(Object object, DeserializeContext context) {
        if (object instanceof Map) {
            return (Map) object;
        }
        throw deserializeError(context.errorPath, "Expected a Map while deserializing, but got a " + object.getClass());
    }

    private static Integer convertInteger(Object o, DeserializeContext context) {
        if (o instanceof Integer) {
            return (Integer) o;
        }
        if ((o instanceof Long) || (o instanceof Double)) {
            double value = ((Number) o).doubleValue();
            if (value >= -2.147483648E9d && value <= 2.147483647E9d) {
                return Integer.valueOf(((Number) o).intValue());
            }
            throw deserializeError(context.errorPath, "Numeric value out of 32-bit integer range: " + value + ". Did you mean to use a long or double instead of an int?");
        }
        throw deserializeError(context.errorPath, "Failed to convert a value of type " + o.getClass().getName() + " to int");
    }

    private static Long convertLong(Object o, DeserializeContext context) {
        if (o instanceof Integer) {
            return Long.valueOf(((Integer) o).longValue());
        }
        if (o instanceof Long) {
            return (Long) o;
        }
        if (o instanceof Double) {
            Double value = (Double) o;
            if (value.doubleValue() >= -9.223372036854776E18d && value.doubleValue() <= 9.223372036854776E18d) {
                return Long.valueOf(value.longValue());
            }
            throw deserializeError(context.errorPath, "Numeric value out of 64-bit long range: " + value + ". Did you mean to use a double instead of a long?");
        }
        throw deserializeError(context.errorPath, "Failed to convert a value of type " + o.getClass().getName() + " to long");
    }

    private static Double convertDouble(Object o, DeserializeContext context) {
        if (o instanceof Integer) {
            return Double.valueOf(((Integer) o).doubleValue());
        }
        if (o instanceof Long) {
            Long value = (Long) o;
            Double doubleValue = Double.valueOf(((Long) o).doubleValue());
            if (doubleValue.longValue() == value.longValue()) {
                return doubleValue;
            }
            throw deserializeError(context.errorPath, "Loss of precision while converting number to double: " + o + ". Did you mean to use a 64-bit long instead?");
        }
        if (o instanceof Double) {
            return (Double) o;
        }
        throw deserializeError(context.errorPath, "Failed to convert a value of type " + o.getClass().getName() + " to double");
    }

    private static Boolean convertBoolean(Object o, DeserializeContext context) {
        if (o instanceof Boolean) {
            return (Boolean) o;
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to boolean");
    }

    private static String convertString(Object o, DeserializeContext context) {
        if (o instanceof String) {
            return (String) o;
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to String");
    }

    private static Date convertDate(Object o, DeserializeContext context) {
        if (o instanceof Date) {
            return (Date) o;
        }
        if (o instanceof Timestamp) {
            return ((Timestamp) o).toDate();
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to Date");
    }

    private static Timestamp convertTimestamp(Object o, DeserializeContext context) {
        if (o instanceof Timestamp) {
            return (Timestamp) o;
        }
        if (o instanceof Date) {
            return new Timestamp((Date) o);
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to Timestamp");
    }

    private static Blob convertBlob(Object o, DeserializeContext context) {
        if (o instanceof Blob) {
            return (Blob) o;
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to Blob");
    }

    private static GeoPoint convertGeoPoint(Object o, DeserializeContext context) {
        if (o instanceof GeoPoint) {
            return (GeoPoint) o;
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to GeoPoint");
    }

    private static DocumentReference convertDocumentReference(Object o, DeserializeContext context) {
        if (o instanceof DocumentReference) {
            return (DocumentReference) o;
        }
        throw deserializeError(context.errorPath, "Failed to convert value of type " + o.getClass().getName() + " to DocumentReference");
    }

    private static <T> T convertBean(Object o, Class<T> clazz, DeserializeContext context) {
        BeanMapper<T> mapper = loadOrCreateBeanMapperForClass(clazz);
        if (o instanceof Map) {
            return mapper.deserialize(expectMap(o, context), context);
        }
        throw deserializeError(context.errorPath, "Can't convert object of type " + o.getClass().getName() + " to type " + clazz.getName());
    }

    private static IllegalArgumentException serializeError(ErrorPath path, String reason) {
        String reason2 = "Could not serialize object. " + reason;
        if (path.getLength() > 0) {
            reason2 = reason2 + " (found in field '" + path.toString() + "')";
        }
        return new IllegalArgumentException(reason2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static RuntimeException deserializeError(ErrorPath path, String reason) {
        String reason2 = "Could not deserialize object. " + reason;
        if (path.getLength() > 0) {
            reason2 = reason2 + " (found in field '" + path.toString() + "')";
        }
        return new RuntimeException(reason2);
    }

    private static class BeanMapper<T> {
        private final Class<T> clazz;
        private final Constructor<T> constructor;
        private final boolean throwOnUnknownProperties;
        private final boolean warnOnUnknownProperties;
        private final Map<String, String> properties = new HashMap();
        private final Map<String, Method> setters = new HashMap();
        private final Map<String, Method> getters = new HashMap();
        private final Map<String, Field> fields = new HashMap();
        private final HashSet<String> serverTimestamps = new HashSet<>();
        private final HashSet<String> documentIdPropertyNames = new HashSet<>();

        BeanMapper(Class<T> clazz) {
            Constructor<T> constructor;
            this.clazz = clazz;
            this.throwOnUnknownProperties = clazz.isAnnotationPresent(ThrowOnExtraProperties.class);
            this.warnOnUnknownProperties = !clazz.isAnnotationPresent(IgnoreExtraProperties.class);
            try {
                constructor = clazz.getDeclaredConstructor(new Class[0]);
                constructor.setAccessible(true);
            } catch (NoSuchMethodException e) {
                constructor = null;
            }
            this.constructor = constructor;
            for (Method method : clazz.getMethods()) {
                if (shouldIncludeGetter(method)) {
                    String propertyName = propertyName(method);
                    addProperty(propertyName);
                    method.setAccessible(true);
                    if (this.getters.containsKey(propertyName)) {
                        throw new RuntimeException("Found conflicting getters for name " + method.getName() + " on class " + clazz.getName());
                    }
                    this.getters.put(propertyName, method);
                    applyGetterAnnotations(method);
                }
            }
            for (Field field : clazz.getFields()) {
                if (shouldIncludeField(field)) {
                    addProperty(propertyName(field));
                    applyFieldAnnotations(field);
                }
            }
            Class<T> superclass = clazz;
            do {
                for (Method method2 : superclass.getDeclaredMethods()) {
                    if (shouldIncludeSetter(method2)) {
                        String propertyName2 = propertyName(method2);
                        String existingPropertyName = this.properties.get(propertyName2.toLowerCase(Locale.US));
                        if (existingPropertyName == null) {
                            continue;
                        } else {
                            if (!existingPropertyName.equals(propertyName2)) {
                                throw new RuntimeException("Found setter on " + superclass.getName() + " with invalid case-sensitive name: " + method2.getName());
                            }
                            Method existingSetter = this.setters.get(propertyName2);
                            if (existingSetter == null) {
                                method2.setAccessible(true);
                                this.setters.put(propertyName2, method2);
                                applySetterAnnotations(method2);
                            } else if (!isSetterOverride(method2, existingSetter)) {
                                if (superclass == clazz) {
                                    throw new RuntimeException("Class " + clazz.getName() + " has multiple setter overloads with name " + method2.getName());
                                }
                                throw new RuntimeException("Found conflicting setters with name: " + method2.getName() + " (conflicts with " + existingSetter.getName() + " defined on " + existingSetter.getDeclaringClass().getName() + ")");
                            }
                        }
                    }
                }
                for (Field field2 : superclass.getDeclaredFields()) {
                    String propertyName3 = propertyName(field2);
                    if (this.properties.containsKey(propertyName3.toLowerCase(Locale.US)) && !this.fields.containsKey(propertyName3)) {
                        field2.setAccessible(true);
                        this.fields.put(propertyName3, field2);
                        applyFieldAnnotations(field2);
                    }
                }
                superclass = superclass.getSuperclass();
                if (superclass == null) {
                    break;
                }
            } while (!superclass.equals(Object.class));
            if (this.properties.isEmpty()) {
                throw new RuntimeException("No properties to serialize found on class " + clazz.getName());
            }
            for (String docIdProperty : this.documentIdPropertyNames) {
                if (!this.setters.containsKey(docIdProperty) && !this.fields.containsKey(docIdProperty)) {
                    throw new RuntimeException("@DocumentId is annotated on property " + docIdProperty + " of class " + clazz.getName() + " but no field or public setter was found");
                }
            }
        }

        private void addProperty(String property) {
            String oldValue = this.properties.put(property.toLowerCase(Locale.US), property);
            if (oldValue != null && !property.equals(oldValue)) {
                throw new RuntimeException("Found two getters or fields with conflicting case sensitivity for property: " + property.toLowerCase(Locale.US));
            }
        }

        T deserialize(Map<String, Object> values, DeserializeContext context) {
            return deserialize(values, Collections.emptyMap(), context);
        }

        T deserialize(Map<String, Object> map, Map<TypeVariable<Class<T>>, Type> map2, DeserializeContext deserializeContext) {
            if (this.constructor == null) {
                throw CustomClassMapper.deserializeError(deserializeContext.errorPath, "Class " + this.clazz.getName() + " does not define a no-argument constructor. If you are using ProGuard, make sure these constructors are not stripped");
            }
            T t = (T) ApiUtil.newInstance(this.constructor);
            HashSet<String> hashSet = new HashSet<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                ErrorPath errorPathChild = deserializeContext.errorPath.child(key);
                if (this.setters.containsKey(key)) {
                    Method method = this.setters.get(key);
                    Type[] genericParameterTypes = method.getGenericParameterTypes();
                    if (genericParameterTypes.length != 1) {
                        throw CustomClassMapper.deserializeError(errorPathChild, "Setter does not have exactly one parameter");
                    }
                    ApiUtil.invoke(method, t, CustomClassMapper.deserializeToType(entry.getValue(), resolveType(genericParameterTypes[0], map2), deserializeContext.newInstanceWithErrorPath(errorPathChild)));
                    hashSet.add(key);
                } else if (this.fields.containsKey(key)) {
                    Field field = this.fields.get(key);
                    try {
                        field.set(t, CustomClassMapper.deserializeToType(entry.getValue(), resolveType(field.getGenericType(), map2), deserializeContext.newInstanceWithErrorPath(errorPathChild)));
                        hashSet.add(key);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    String str = "No setter/field for " + key + " found on class " + this.clazz.getName();
                    if (this.properties.containsKey(key.toLowerCase(Locale.US))) {
                        str = str + " (fields/setters are case sensitive!)";
                    }
                    if (this.throwOnUnknownProperties) {
                        throw new RuntimeException(str);
                    }
                    if (this.warnOnUnknownProperties) {
                        Logger.warn(CustomClassMapper.class.getSimpleName(), "%s", str);
                    }
                }
            }
            populateDocumentIdProperties(map2, deserializeContext, t, hashSet);
            return t;
        }

        private void populateDocumentIdProperties(Map<TypeVariable<Class<T>>, Type> types, DeserializeContext context, T instance, HashSet<String> deserialzedProperties) {
            for (String docIdPropertyName : this.documentIdPropertyNames) {
                if (deserialzedProperties.contains(docIdPropertyName)) {
                    String message = "'" + docIdPropertyName + "' was found from document " + context.documentRef.getPath() + ", cannot apply @DocumentId on this property for class " + this.clazz.getName();
                    throw new RuntimeException(message);
                }
                ErrorPath childPath = context.errorPath.child(docIdPropertyName);
                if (this.setters.containsKey(docIdPropertyName)) {
                    Method setter = this.setters.get(docIdPropertyName);
                    Type[] params = setter.getGenericParameterTypes();
                    if (params.length != 1) {
                        throw CustomClassMapper.deserializeError(childPath, "Setter does not have exactly one parameter");
                    }
                    Type resolvedType = resolveType(params[0], types);
                    if (resolvedType == String.class) {
                        ApiUtil.invoke(setter, instance, context.documentRef.getId());
                    } else {
                        ApiUtil.invoke(setter, instance, context.documentRef);
                    }
                } else {
                    Field docIdField = this.fields.get(docIdPropertyName);
                    try {
                        if (docIdField.getType() == String.class) {
                            docIdField.set(instance, context.documentRef.getId());
                        } else {
                            docIdField.set(instance, context.documentRef);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        private Type resolveType(Type type, Map<TypeVariable<Class<T>>, Type> types) {
            if (type instanceof TypeVariable) {
                Type resolvedType = types.get(type);
                if (resolvedType == null) {
                    throw new IllegalStateException("Could not resolve type " + type);
                }
                return resolvedType;
            }
            return type;
        }

        Map<String, Object> serialize(T object, ErrorPath path) {
            Object propertyValue;
            Object serializedValue;
            if (!this.clazz.isAssignableFrom(object.getClass())) {
                throw new IllegalArgumentException("Can't serialize object of class " + object.getClass() + " with BeanMapper for class " + this.clazz);
            }
            Map<String, Object> result = new HashMap<>();
            for (String property : this.properties.values()) {
                if (!this.documentIdPropertyNames.contains(property)) {
                    if (this.getters.containsKey(property)) {
                        Method getter = this.getters.get(property);
                        propertyValue = ApiUtil.invoke(getter, object, new Object[0]);
                    } else {
                        Field field = this.fields.get(property);
                        if (field == null) {
                            throw new IllegalStateException("Bean property without field or getter: " + property);
                        }
                        try {
                            Object propertyValue2 = field.get(object);
                            propertyValue = propertyValue2;
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (!this.serverTimestamps.contains(property) || propertyValue != null) {
                        serializedValue = CustomClassMapper.serialize(propertyValue, path.child(property));
                    } else {
                        serializedValue = FieldValue.serverTimestamp();
                    }
                    result.put(property, serializedValue);
                }
            }
            return result;
        }

        private void applyFieldAnnotations(Field field) {
            if (field.isAnnotationPresent(ServerTimestamp.class)) {
                Class<?> fieldType = field.getType();
                if (fieldType != Date.class && fieldType != Timestamp.class) {
                    throw new IllegalArgumentException("Field " + field.getName() + " is annotated with @ServerTimestamp but is " + fieldType + " instead of Date or Timestamp.");
                }
                this.serverTimestamps.add(propertyName(field));
            }
            if (field.isAnnotationPresent(DocumentId.class)) {
                ensureValidDocumentIdType("Field", "is", field.getType());
                this.documentIdPropertyNames.add(propertyName(field));
            }
        }

        private void applyGetterAnnotations(Method method) {
            if (method.isAnnotationPresent(ServerTimestamp.class)) {
                Class<?> returnType = method.getReturnType();
                if (returnType != Date.class && returnType != Timestamp.class) {
                    throw new IllegalArgumentException("Method " + method.getName() + " is annotated with @ServerTimestamp but returns " + returnType + " instead of Date or Timestamp.");
                }
                this.serverTimestamps.add(propertyName(method));
            }
            if (method.isAnnotationPresent(DocumentId.class)) {
                ensureValidDocumentIdType("Method", "returns", method.getReturnType());
                this.documentIdPropertyNames.add(propertyName(method));
            }
        }

        private void applySetterAnnotations(Method method) {
            if (method.isAnnotationPresent(ServerTimestamp.class)) {
                throw new IllegalArgumentException("Method " + method.getName() + " is annotated with @ServerTimestamp but should not be. @ServerTimestamp can only be applied to fields and getters, not setters.");
            }
            if (method.isAnnotationPresent(DocumentId.class)) {
                Class<?> paramType = method.getParameterTypes()[0];
                ensureValidDocumentIdType("Method", "accepts", paramType);
                this.documentIdPropertyNames.add(propertyName(method));
            }
        }

        private void ensureValidDocumentIdType(String fieldDescription, String operation, Type type) {
            if (type != String.class && type != DocumentReference.class) {
                throw new IllegalArgumentException(fieldDescription + " is annotated with @DocumentId but " + operation + " " + type + " instead of String or DocumentReference.");
            }
        }

        private static boolean shouldIncludeGetter(Method method) {
            return ((!method.getName().startsWith("get") && !method.getName().startsWith("is")) || method.getDeclaringClass().equals(Object.class) || !Modifier.isPublic(method.getModifiers()) || Modifier.isStatic(method.getModifiers()) || method.getReturnType().equals(Void.TYPE) || method.getParameterTypes().length != 0 || method.isAnnotationPresent(Exclude.class)) ? false : true;
        }

        private static boolean shouldIncludeSetter(Method method) {
            return method.getName().startsWith("set") && !method.getDeclaringClass().equals(Object.class) && !Modifier.isStatic(method.getModifiers()) && method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 1 && !method.isAnnotationPresent(Exclude.class);
        }

        private static boolean shouldIncludeField(Field field) {
            return (field.getDeclaringClass().equals(Object.class) || !Modifier.isPublic(field.getModifiers()) || Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers()) || field.isAnnotationPresent(Exclude.class)) ? false : true;
        }

        private static boolean isSetterOverride(Method base, Method override) {
            CustomClassMapper.hardAssert(base.getDeclaringClass().isAssignableFrom(override.getDeclaringClass()), "Expected override from a base class");
            CustomClassMapper.hardAssert(base.getReturnType().equals(Void.TYPE), "Expected void return type");
            CustomClassMapper.hardAssert(override.getReturnType().equals(Void.TYPE), "Expected void return type");
            Type[] baseParameterTypes = base.getParameterTypes();
            Type[] overrideParameterTypes = override.getParameterTypes();
            CustomClassMapper.hardAssert(baseParameterTypes.length == 1, "Expected exactly one parameter");
            CustomClassMapper.hardAssert(overrideParameterTypes.length == 1, "Expected exactly one parameter");
            return base.getName().equals(override.getName()) && baseParameterTypes[0].equals(overrideParameterTypes[0]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String propertyName(Field field) {
            String annotatedName = annotatedName(field);
            return annotatedName != null ? annotatedName : field.getName();
        }

        private static String propertyName(Method method) {
            String annotatedName = annotatedName(method);
            return annotatedName != null ? annotatedName : serializedName(method.getName());
        }

        private static String annotatedName(AccessibleObject obj) {
            if (obj.isAnnotationPresent(PropertyName.class)) {
                PropertyName annotation = (PropertyName) obj.getAnnotation(PropertyName.class);
                return annotation.value();
            }
            return null;
        }

        private static String serializedName(String methodName) {
            String[] prefixes = {"get", "set", "is"};
            String methodPrefix = null;
            for (String prefix : prefixes) {
                if (methodName.startsWith(prefix)) {
                    methodPrefix = prefix;
                }
            }
            if (methodPrefix == null) {
                throw new IllegalArgumentException("Unknown Bean prefix for method: " + methodName);
            }
            String strippedName = methodName.substring(methodPrefix.length());
            char[] chars = strippedName.toCharArray();
            for (int pos = 0; pos < chars.length && Character.isUpperCase(chars[pos]); pos++) {
                chars[pos] = Character.toLowerCase(chars[pos]);
            }
            return new String(chars);
        }
    }

    static class ErrorPath {
        static final ErrorPath EMPTY = new ErrorPath(null, null, 0);
        private final int length;
        private final String name;
        private final ErrorPath parent;

        ErrorPath(ErrorPath parent, String name, int length) {
            this.parent = parent;
            this.name = name;
            this.length = length;
        }

        int getLength() {
            return this.length;
        }

        ErrorPath child(String name) {
            return new ErrorPath(this, name, this.length + 1);
        }

        public String toString() {
            if (this.length == 0) {
                return "";
            }
            if (this.length == 1) {
                return this.name;
            }
            return this.parent.toString() + "." + this.name;
        }
    }

    static class DeserializeContext {
        final DocumentReference documentRef;
        final ErrorPath errorPath;

        DeserializeContext(ErrorPath path, DocumentReference docRef) {
            this.errorPath = path;
            this.documentRef = docRef;
        }

        DeserializeContext newInstanceWithErrorPath(ErrorPath newPath) {
            return new DeserializeContext(newPath, this.documentRef);
        }
    }
}

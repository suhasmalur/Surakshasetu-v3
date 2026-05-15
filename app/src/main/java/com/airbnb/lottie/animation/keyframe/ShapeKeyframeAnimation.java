package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import com.airbnb.lottie.animation.content.ShapeModifierContent;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ShapeKeyframeAnimation extends BaseKeyframeAnimation<ShapeData, Path> {
    private List<ShapeModifierContent> shapeModifiers;
    private final Path tempPath;
    private final ShapeData tempShapeData;
    private Path valueCallbackEndPath;
    private Path valueCallbackStartPath;

    public ShapeKeyframeAnimation(List<Keyframe<ShapeData>> keyframes) {
        super(keyframes);
        this.tempShapeData = new ShapeData();
        this.tempPath = new Path();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public Path getValue(Keyframe<ShapeData> keyframe, float keyframeProgress) {
        ShapeData startShapeData = keyframe.startValue;
        ShapeData endShapeData = keyframe.endValue;
        this.tempShapeData.interpolateBetween(startShapeData, endShapeData == null ? startShapeData : endShapeData, keyframeProgress);
        ShapeData modifiedShapeData = this.tempShapeData;
        if (this.shapeModifiers != null) {
            for (int i = this.shapeModifiers.size() - 1; i >= 0; i--) {
                modifiedShapeData = this.shapeModifiers.get(i).modifyShape(modifiedShapeData);
            }
        }
        MiscUtils.getPathFromData(modifiedShapeData, this.tempPath);
        if (this.valueCallback != null) {
            if (this.valueCallbackStartPath == null) {
                this.valueCallbackStartPath = new Path();
                this.valueCallbackEndPath = new Path();
            }
            MiscUtils.getPathFromData(startShapeData, this.valueCallbackStartPath);
            if (endShapeData != null) {
                MiscUtils.getPathFromData(endShapeData, this.valueCallbackEndPath);
            }
            return (Path) this.valueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), this.valueCallbackStartPath, endShapeData == null ? this.valueCallbackStartPath : this.valueCallbackEndPath, keyframeProgress, getLinearCurrentKeyframeProgress(), getProgress());
        }
        return this.tempPath;
    }

    public void setShapeModifiers(List<ShapeModifierContent> shapeModifiers) {
        this.shapeModifiers = shapeModifiers;
    }
}

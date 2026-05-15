package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.motion.utils.Utils;
import com.google.common.base.Ascii;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class MotionKeyCycle extends MotionKey {
    public static final int KEY_TYPE = 4;
    static final String NAME = "KeyCycle";
    public static final int SHAPE_BOUNCE = 6;
    public static final int SHAPE_COS_WAVE = 5;
    public static final int SHAPE_REVERSE_SAW_WAVE = 4;
    public static final int SHAPE_SAW_WAVE = 3;
    public static final int SHAPE_SIN_WAVE = 0;
    public static final int SHAPE_SQUARE_WAVE = 1;
    public static final int SHAPE_TRIANGLE_WAVE = 2;
    private static final String TAG = "KeyCycle";
    public static final String WAVE_OFFSET = "waveOffset";
    public static final String WAVE_PERIOD = "wavePeriod";
    public static final String WAVE_PHASE = "wavePhase";
    public static final String WAVE_SHAPE = "waveShape";
    private String mTransitionEasing = null;
    private int mCurveFit = 0;
    private int mWaveShape = -1;
    private String mCustomWaveShape = null;
    private float mWavePeriod = Float.NaN;
    private float mWaveOffset = 0.0f;
    private float mWavePhase = 0.0f;
    private float mProgress = Float.NaN;
    private float mAlpha = Float.NaN;
    private float mElevation = Float.NaN;
    private float mRotation = Float.NaN;
    private float mTransitionPathRotate = Float.NaN;
    private float mRotationX = Float.NaN;
    private float mRotationY = Float.NaN;
    private float mScaleX = Float.NaN;
    private float mScaleY = Float.NaN;
    private float mTranslationX = Float.NaN;
    private float mTranslationY = Float.NaN;
    private float mTranslationZ = Float.NaN;

    public MotionKeyCycle() {
        this.mType = 4;
        this.mCustom = new HashMap<>();
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void getAttributeNames(HashSet<String> attributes) {
        if (!Float.isNaN(this.mAlpha)) {
            attributes.add("alpha");
        }
        if (!Float.isNaN(this.mElevation)) {
            attributes.add("elevation");
        }
        if (!Float.isNaN(this.mRotation)) {
            attributes.add("rotationZ");
        }
        if (!Float.isNaN(this.mRotationX)) {
            attributes.add("rotationX");
        }
        if (!Float.isNaN(this.mRotationY)) {
            attributes.add("rotationY");
        }
        if (!Float.isNaN(this.mScaleX)) {
            attributes.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            attributes.add("scaleY");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            attributes.add("pathRotate");
        }
        if (!Float.isNaN(this.mTranslationX)) {
            attributes.add("translationX");
        }
        if (!Float.isNaN(this.mTranslationY)) {
            attributes.add("translationY");
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            attributes.add("translationZ");
        }
        if (this.mCustom.size() > 0) {
            for (String s : this.mCustom.keySet()) {
                attributes.add("CUSTOM," + s);
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void addValues(HashMap<String, SplineSet> splines) {
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int type, int value) {
        switch (type) {
            case TypedValues.CycleType.TYPE_CURVE_FIT /* 401 */:
                this.mCurveFit = value;
                break;
            case TypedValues.CycleType.TYPE_WAVE_SHAPE /* 421 */:
                this.mWaveShape = value;
                break;
            default:
                boolean ret = setValue(type, value);
                if (!ret) {
                    break;
                }
                break;
        }
        return true;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int type, String value) {
        switch (type) {
            case TypedValues.CycleType.TYPE_EASING /* 420 */:
                this.mTransitionEasing = value;
                return true;
            case TypedValues.CycleType.TYPE_WAVE_SHAPE /* 421 */:
            default:
                return super.setValue(type, value);
            case TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE /* 422 */:
                this.mCustomWaveShape = value;
                return true;
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int type, float value) {
        switch (type) {
            case 304:
                this.mTranslationX = value;
                return true;
            case 305:
                this.mTranslationY = value;
                return true;
            case 306:
                this.mTranslationZ = value;
                return true;
            case 307:
                this.mElevation = value;
                return true;
            case 308:
                this.mRotationX = value;
                return true;
            case 309:
                this.mRotationY = value;
                return true;
            case 310:
                this.mRotation = value;
                return true;
            case 311:
                this.mScaleX = value;
                return true;
            case 312:
                this.mScaleY = value;
                return true;
            case 315:
                this.mProgress = value;
                return true;
            case TypedValues.CycleType.TYPE_ALPHA /* 403 */:
                this.mAlpha = value;
                return true;
            case TypedValues.CycleType.TYPE_PATH_ROTATE /* 416 */:
                this.mTransitionPathRotate = value;
                return true;
            case TypedValues.CycleType.TYPE_WAVE_PERIOD /* 423 */:
                this.mWavePeriod = value;
                return true;
            case TypedValues.CycleType.TYPE_WAVE_OFFSET /* 424 */:
                this.mWaveOffset = value;
                return true;
            case TypedValues.CycleType.TYPE_WAVE_PHASE /* 425 */:
                this.mWavePhase = value;
                return true;
            default:
                return super.setValue(type, value);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float getValue(java.lang.String r2) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.key.MotionKeyCycle.getValue(java.lang.String):float");
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    /* JADX INFO: renamed from: clone */
    public MotionKey mo12clone() {
        return null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String name) {
        byte b;
        switch (name.hashCode()) {
            case -1581616630:
                b = !name.equals(TypedValues.CycleType.S_CUSTOM_WAVE_SHAPE) ? (byte) -1 : Ascii.DC4;
                break;
            case -1310311125:
                b = !name.equals("easing") ? (byte) -1 : Ascii.SI;
                break;
            case -1249320806:
                b = !name.equals("rotationX") ? (byte) -1 : (byte) 6;
                break;
            case -1249320805:
                b = !name.equals("rotationY") ? (byte) -1 : (byte) 7;
                break;
            case -1249320804:
                b = !name.equals("rotationZ") ? (byte) -1 : (byte) 8;
                break;
            case -1225497657:
                b = !name.equals("translationX") ? (byte) -1 : (byte) 3;
                break;
            case -1225497656:
                b = !name.equals("translationY") ? (byte) -1 : (byte) 4;
                break;
            case -1225497655:
                b = !name.equals("translationZ") ? (byte) -1 : (byte) 5;
                break;
            case -1019779949:
                b = !name.equals(TypedValues.CycleType.S_WAVE_OFFSET) ? (byte) -1 : (byte) 19;
                break;
            case -1001078227:
                b = !name.equals("progress") ? (byte) -1 : Ascii.CR;
                break;
            case -991726143:
                b = !name.equals(TypedValues.CycleType.S_WAVE_PERIOD) ? (byte) -1 : Ascii.DLE;
                break;
            case -987906986:
                b = !name.equals("pivotX") ? (byte) -1 : Ascii.VT;
                break;
            case -987906985:
                b = !name.equals("pivotY") ? (byte) -1 : Ascii.FF;
                break;
            case -908189618:
                b = !name.equals("scaleX") ? (byte) -1 : (byte) 9;
                break;
            case -908189617:
                b = !name.equals("scaleY") ? (byte) -1 : (byte) 10;
                break;
            case 92909918:
                b = !name.equals("alpha") ? (byte) -1 : (byte) 2;
                break;
            case 106629499:
                b = !name.equals(TypedValues.CycleType.S_WAVE_PHASE) ? (byte) -1 : Ascii.DC2;
                break;
            case 579057826:
                b = !name.equals("curveFit") ? (byte) -1 : (byte) 0;
                break;
            case 803192288:
                b = !name.equals("pathRotate") ? (byte) -1 : Ascii.SO;
                break;
            case 1532805160:
                b = !name.equals("waveShape") ? (byte) -1 : (byte) 17;
                break;
            case 1941332754:
                b = !name.equals("visibility") ? (byte) -1 : (byte) 1;
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
                return TypedValues.CycleType.TYPE_CURVE_FIT;
            case 1:
                return TypedValues.CycleType.TYPE_VISIBILITY;
            case 2:
                return TypedValues.CycleType.TYPE_ALPHA;
            case 3:
                return 304;
            case 4:
                return 305;
            case 5:
                return 306;
            case 6:
                return 308;
            case 7:
                return 309;
            case 8:
                return 310;
            case 9:
                return 311;
            case 10:
                return 312;
            case 11:
                return 313;
            case 12:
                return 314;
            case 13:
                return 315;
            case 14:
                return TypedValues.CycleType.TYPE_PATH_ROTATE;
            case 15:
                return TypedValues.CycleType.TYPE_EASING;
            case 16:
                return TypedValues.CycleType.TYPE_WAVE_PERIOD;
            case 17:
                return TypedValues.CycleType.TYPE_WAVE_SHAPE;
            case 18:
                return TypedValues.CycleType.TYPE_WAVE_PHASE;
            case 19:
                return TypedValues.CycleType.TYPE_WAVE_OFFSET;
            case 20:
                return TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE;
            default:
                return -1;
        }
    }

    public void addCycleValues(HashMap<String, KeyCycleOscillator> oscSet) {
        KeyCycleOscillator osc;
        KeyCycleOscillator osc2;
        for (String key : oscSet.keySet()) {
            if (key.startsWith("CUSTOM")) {
                String customKey = key.substring("CUSTOM".length() + 1);
                CustomVariable cValue = this.mCustom.get(customKey);
                if (cValue != null && cValue.getType() == 901 && (osc = oscSet.get(key)) != null) {
                    osc.setPoint(this.mFramePosition, this.mWaveShape, this.mCustomWaveShape, -1, this.mWavePeriod, this.mWaveOffset, this.mWavePhase, cValue.getValueToInterpolate(), cValue);
                }
            } else {
                float value = getValue(key);
                if (!Float.isNaN(value) && (osc2 = oscSet.get(key)) != null) {
                    osc2.setPoint(this.mFramePosition, this.mWaveShape, this.mCustomWaveShape, -1, this.mWavePeriod, this.mWaveOffset, this.mWavePhase, value);
                }
            }
        }
    }

    public void dump() {
        System.out.println("MotionKeyCycle{mWaveShape=" + this.mWaveShape + ", mWavePeriod=" + this.mWavePeriod + ", mWaveOffset=" + this.mWaveOffset + ", mWavePhase=" + this.mWavePhase + ", mRotation=" + this.mRotation + '}');
    }

    public void printAttributes() {
        HashSet<String> nameSet = new HashSet<>();
        getAttributeNames(nameSet);
        Utils.log(" ------------- " + this.mFramePosition + " -------------");
        Utils.log("MotionKeyCycle{Shape=" + this.mWaveShape + ", Period=" + this.mWavePeriod + ", Offset=" + this.mWaveOffset + ", Phase=" + this.mWavePhase + '}');
        String[] names = (String[]) nameSet.toArray(new String[0]);
        for (int i = 0; i < names.length; i++) {
            TypedValues.AttributesType.getId(names[i]);
            Utils.log(names[i] + ":" + getValue(names[i]));
        }
    }
}

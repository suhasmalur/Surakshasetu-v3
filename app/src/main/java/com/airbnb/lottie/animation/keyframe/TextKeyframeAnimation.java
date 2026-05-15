package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TextKeyframeAnimation extends KeyframeAnimation<DocumentData> {
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* bridge */ /* synthetic */ Object getValue(Keyframe keyframe, float f) {
        return getValue((Keyframe<DocumentData>) keyframe, f);
    }

    public TextKeyframeAnimation(List<Keyframe<DocumentData>> keyframes) {
        super(keyframes);
    }

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
    DocumentData getValue(Keyframe<DocumentData> keyframe, float keyframeProgress) {
        if (this.valueCallback != null) {
            return (DocumentData) this.valueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame == null ? Float.MAX_VALUE : keyframe.endFrame.floatValue(), keyframe.startValue, keyframe.endValue == null ? keyframe.startValue : keyframe.endValue, keyframeProgress, getInterpolatedCurrentKeyframeProgress(), getProgress());
        }
        if (keyframeProgress != 1.0f || keyframe.endValue == null) {
            return keyframe.startValue;
        }
        return keyframe.endValue;
    }

    public void setStringValueCallback(final LottieValueCallback<String> valueCallback) {
        final LottieFrameInfo<String> stringFrameInfo = new LottieFrameInfo<>();
        final DocumentData documentData = new DocumentData();
        super.setValueCallback(new LottieValueCallback<DocumentData>() { // from class: com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.airbnb.lottie.value.LottieValueCallback
            public DocumentData getValue(LottieFrameInfo<DocumentData> frameInfo) {
                stringFrameInfo.set(frameInfo.getStartFrame(), frameInfo.getEndFrame(), frameInfo.getStartValue().text, frameInfo.getEndValue().text, frameInfo.getLinearKeyframeProgress(), frameInfo.getInterpolatedKeyframeProgress(), frameInfo.getOverallProgress());
                String text = (String) valueCallback.getValue(stringFrameInfo);
                DocumentData baseDocumentData = frameInfo.getInterpolatedKeyframeProgress() == 1.0f ? frameInfo.getEndValue() : frameInfo.getStartValue();
                documentData.set(text, baseDocumentData.fontName, baseDocumentData.size, baseDocumentData.justification, baseDocumentData.tracking, baseDocumentData.lineHeight, baseDocumentData.baselineShift, baseDocumentData.color, baseDocumentData.strokeColor, baseDocumentData.strokeWidth, baseDocumentData.strokeOverFill, baseDocumentData.boxPosition, baseDocumentData.boxSize);
                return documentData;
            }
        });
    }
}

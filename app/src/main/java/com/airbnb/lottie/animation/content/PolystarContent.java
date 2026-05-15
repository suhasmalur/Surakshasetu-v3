package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PolystarContent implements PathContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private static final float POLYGON_MAGIC_NUMBER = 0.25f;
    private static final float POLYSTAR_MAGIC_NUMBER = 0.47829f;
    private final boolean hidden;
    private final BaseKeyframeAnimation<?, Float> innerRadiusAnimation;
    private final BaseKeyframeAnimation<?, Float> innerRoundednessAnimation;
    private boolean isPathValid;
    private final boolean isReversed;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<?, Float> outerRadiusAnimation;
    private final BaseKeyframeAnimation<?, Float> outerRoundednessAnimation;
    private final BaseKeyframeAnimation<?, Float> pointsAnimation;
    private final BaseKeyframeAnimation<?, PointF> positionAnimation;
    private final BaseKeyframeAnimation<?, Float> rotationAnimation;
    private final PolystarShape.Type type;
    private final Path path = new Path();
    private final Path lastSegmentPath = new Path();
    private final PathMeasure lastSegmentPathMeasure = new PathMeasure();
    private final float[] lastSegmentPosition = new float[2];
    private final CompoundTrimPathContent trimPaths = new CompoundTrimPathContent();

    public PolystarContent(LottieDrawable lottieDrawable, BaseLayer layer, PolystarShape polystarShape) {
        this.lottieDrawable = lottieDrawable;
        this.name = polystarShape.getName();
        this.type = polystarShape.getType();
        this.hidden = polystarShape.isHidden();
        this.isReversed = polystarShape.isReversed();
        this.pointsAnimation = polystarShape.getPoints().createAnimation();
        this.positionAnimation = polystarShape.getPosition().createAnimation();
        this.rotationAnimation = polystarShape.getRotation().createAnimation();
        this.outerRadiusAnimation = polystarShape.getOuterRadius().createAnimation();
        this.outerRoundednessAnimation = polystarShape.getOuterRoundedness().createAnimation();
        if (this.type == PolystarShape.Type.STAR) {
            this.innerRadiusAnimation = polystarShape.getInnerRadius().createAnimation();
            this.innerRoundednessAnimation = polystarShape.getInnerRoundedness().createAnimation();
        } else {
            this.innerRadiusAnimation = null;
            this.innerRoundednessAnimation = null;
        }
        layer.addAnimation(this.pointsAnimation);
        layer.addAnimation(this.positionAnimation);
        layer.addAnimation(this.rotationAnimation);
        layer.addAnimation(this.outerRadiusAnimation);
        layer.addAnimation(this.outerRoundednessAnimation);
        if (this.type == PolystarShape.Type.STAR) {
            layer.addAnimation(this.innerRadiusAnimation);
            layer.addAnimation(this.innerRoundednessAnimation);
        }
        this.pointsAnimation.addUpdateListener(this);
        this.positionAnimation.addUpdateListener(this);
        this.rotationAnimation.addUpdateListener(this);
        this.outerRadiusAnimation.addUpdateListener(this);
        this.outerRoundednessAnimation.addUpdateListener(this);
        if (this.type == PolystarShape.Type.STAR) {
            this.innerRadiusAnimation.addUpdateListener(this);
            this.innerRoundednessAnimation.addUpdateListener(this);
        }
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        invalidate();
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> contentsBefore, List<Content> contentsAfter) {
        for (int i = 0; i < contentsBefore.size(); i++) {
            Content content = contentsBefore.get(i);
            if ((content instanceof TrimPathContent) && ((TrimPathContent) content).getType() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                TrimPathContent trimPath = (TrimPathContent) content;
                this.trimPaths.addTrimPath(trimPath);
                trimPath.addListener(this);
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path getPath() {
        if (this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        if (this.hidden) {
            this.isPathValid = true;
            return this.path;
        }
        switch (this.type) {
            case STAR:
                createStarPath();
                break;
            case POLYGON:
                createPolygonPath();
                break;
        }
        this.path.close();
        this.trimPaths.apply(this.path);
        this.isPathValid = true;
        return this.path;
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0247  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void createStarPath() {
        /*
            Method dump skipped, instruction units count: 633
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.PolystarContent.createStarPath():void");
    }

    private void createPolygonPath() {
        int points;
        double currentAngle;
        float anglePerPoint;
        float roundedness;
        float anglePerPoint2;
        float previousY;
        PolystarContent polystarContent = this;
        int points2 = (int) Math.floor(polystarContent.pointsAnimation.getValue().floatValue());
        double currentAngle2 = Math.toRadians((polystarContent.rotationAnimation == null ? 0.0d : polystarContent.rotationAnimation.getValue().floatValue()) - 90.0d);
        float previousX = (float) (6.283185307179586d / ((double) points2));
        float roundedness2 = polystarContent.outerRoundednessAnimation.getValue().floatValue() / 100.0f;
        float radius = polystarContent.outerRadiusAnimation.getValue().floatValue();
        float x = (float) (((double) radius) * Math.cos(currentAngle2));
        float y = (float) (((double) radius) * Math.sin(currentAngle2));
        polystarContent.path.moveTo(x, y);
        double currentAngle3 = currentAngle2 + ((double) previousX);
        double numPoints = Math.ceil(points2);
        int i = 0;
        while (i < numPoints) {
            float previousX2 = x;
            float previousY2 = y;
            x = (float) (((double) radius) * Math.cos(currentAngle3));
            y = (float) (((double) radius) * Math.sin(currentAngle3));
            if (roundedness2 != 0.0f) {
                points = points2;
                currentAngle = currentAngle3;
                anglePerPoint = previousX;
                float cp1Theta = (float) (Math.atan2(previousY2, previousX2) - 1.5707963267948966d);
                float cp1Dx = (float) Math.cos(cp1Theta);
                float cp1Dy = (float) Math.sin(cp1Theta);
                float cp2Theta = (float) (Math.atan2(y, x) - 1.5707963267948966d);
                float cp2Dx = (float) Math.cos(cp2Theta);
                float cp2Dy = (float) Math.sin(cp2Theta);
                float cp1x = radius * roundedness2 * POLYGON_MAGIC_NUMBER * cp1Dx;
                float cp1y = radius * roundedness2 * POLYGON_MAGIC_NUMBER * cp1Dy;
                float cp2x = radius * roundedness2 * POLYGON_MAGIC_NUMBER * cp2Dx;
                float cp2y = radius * roundedness2 * POLYGON_MAGIC_NUMBER * cp2Dy;
                if (i == numPoints - 1.0d) {
                    polystarContent = this;
                    polystarContent.lastSegmentPath.reset();
                    previousY = previousY2;
                    polystarContent.lastSegmentPath.moveTo(previousX2, previousY);
                    polystarContent.lastSegmentPath.cubicTo(previousX2 - cp1x, previousY - cp1y, x + cp2x, y + cp2y, x, y);
                    polystarContent.lastSegmentPathMeasure.setPath(polystarContent.lastSegmentPath, false);
                    roundedness = roundedness2;
                    polystarContent.lastSegmentPathMeasure.getPosTan(polystarContent.lastSegmentPathMeasure.getLength() * 0.9999f, polystarContent.lastSegmentPosition, null);
                    polystarContent.path.cubicTo(previousX2 - cp1x, previousY - cp1y, x + cp2x, y + cp2y, polystarContent.lastSegmentPosition[0], polystarContent.lastSegmentPosition[1]);
                } else {
                    polystarContent = this;
                    roundedness = roundedness2;
                    previousY = previousY2;
                    polystarContent.path.cubicTo(previousX2 - cp1x, previousY - cp1y, x + cp2x, y + cp2y, x, y);
                }
            } else {
                points = points2;
                currentAngle = currentAngle3;
                anglePerPoint = previousX;
                roundedness = roundedness2;
                if (i == numPoints - 1.0d) {
                    anglePerPoint2 = anglePerPoint;
                    i++;
                    previousX = anglePerPoint2;
                    points2 = points;
                    currentAngle3 = currentAngle;
                    roundedness2 = roundedness;
                } else {
                    polystarContent.path.lineTo(x, y);
                }
            }
            anglePerPoint2 = anglePerPoint;
            currentAngle += (double) anglePerPoint2;
            i++;
            previousX = anglePerPoint2;
            points2 = points;
            currentAngle3 = currentAngle;
            roundedness2 = roundedness;
        }
        PointF position = polystarContent.positionAnimation.getValue();
        polystarContent.path.offset(position.x, position.y);
        polystarContent.path.close();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        MiscUtils.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        if (property == LottieProperty.POLYSTAR_POINTS) {
            this.pointsAnimation.setValueCallback(callback);
            return;
        }
        if (property == LottieProperty.POLYSTAR_ROTATION) {
            this.rotationAnimation.setValueCallback(callback);
            return;
        }
        if (property == LottieProperty.POSITION) {
            this.positionAnimation.setValueCallback(callback);
            return;
        }
        if (property == LottieProperty.POLYSTAR_INNER_RADIUS && this.innerRadiusAnimation != null) {
            this.innerRadiusAnimation.setValueCallback(callback);
            return;
        }
        if (property == LottieProperty.POLYSTAR_OUTER_RADIUS) {
            this.outerRadiusAnimation.setValueCallback(callback);
            return;
        }
        if (property == LottieProperty.POLYSTAR_INNER_ROUNDEDNESS && this.innerRoundednessAnimation != null) {
            this.innerRoundednessAnimation.setValueCallback(callback);
        } else if (property == LottieProperty.POLYSTAR_OUTER_ROUNDEDNESS) {
            this.outerRoundednessAnimation.setValueCallback(callback);
        }
    }
}

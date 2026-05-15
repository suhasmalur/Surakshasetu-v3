package com.airbnb.lottie.animation.content;

import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.RoundedCorners;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RoundedCornersContent implements ShapeModifierContent, BaseKeyframeAnimation.AnimationListener {
    private static final float ROUNDED_CORNER_MAGIC_NUMBER = 0.5519f;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<Float, Float> roundedCorners;
    private ShapeData shapeData;

    public RoundedCornersContent(LottieDrawable lottieDrawable, BaseLayer layer, RoundedCorners roundedCorners) {
        this.lottieDrawable = lottieDrawable;
        this.name = roundedCorners.getName();
        this.roundedCorners = roundedCorners.getCornerRadius().createAnimation();
        layer.addAnimation(this.roundedCorners);
        this.roundedCorners.addUpdateListener(this);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> contentsBefore, List<Content> contentsAfter) {
    }

    public BaseKeyframeAnimation<Float, Float> getRoundedCorners() {
        return this.roundedCorners;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0189  */
    @Override // com.airbnb.lottie.animation.content.ShapeModifierContent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.airbnb.lottie.model.content.ShapeData modifyShape(com.airbnb.lottie.model.content.ShapeData r37) {
        /*
            Method dump skipped, instruction units count: 493
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.RoundedCornersContent.modifyShape(com.airbnb.lottie.model.content.ShapeData):com.airbnb.lottie.model.content.ShapeData");
    }

    private ShapeData getShapeData(ShapeData startingShapeData) {
        List<CubicCurveData> startingCurves = startingShapeData.getCurves();
        boolean isClosed = startingShapeData.isClosed();
        int vertices = 0;
        int i = startingCurves.size() - 1;
        while (true) {
            boolean isEndOfCurve = false;
            if (i < 0) {
                break;
            }
            CubicCurveData startingCurve = startingCurves.get(i);
            CubicCurveData previousCurve = startingCurves.get(floorMod(i - 1, startingCurves.size()));
            PointF vertex = (i != 0 || isClosed) ? previousCurve.getVertex() : startingShapeData.getInitialPoint();
            PointF inPoint = (i != 0 || isClosed) ? previousCurve.getControlPoint2() : vertex;
            PointF outPoint = startingCurve.getControlPoint1();
            if (!startingShapeData.isClosed() && (i == 0 || i == startingCurves.size() - 1)) {
                isEndOfCurve = true;
            }
            if (inPoint.equals(vertex) && outPoint.equals(vertex) && !isEndOfCurve) {
                vertices += 2;
            } else {
                vertices++;
            }
            i--;
        }
        if (this.shapeData == null || this.shapeData.getCurves().size() != vertices) {
            List<CubicCurveData> newCurves = new ArrayList<>(vertices);
            for (int i2 = 0; i2 < vertices; i2++) {
                newCurves.add(new CubicCurveData());
            }
            this.shapeData = new ShapeData(new PointF(0.0f, 0.0f), false, newCurves);
        }
        this.shapeData.setClosed(isClosed);
        return this.shapeData;
    }

    private static int floorMod(int x, int y) {
        return x - (floorDiv(x, y) * y);
    }

    private static int floorDiv(int x, int y) {
        int r = x / y;
        if ((x ^ y) < 0 && r * y != x) {
            return r - 1;
        }
        return r;
    }
}

package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class DropShadowEffectParser {
    private static final JsonReader.Options DROP_SHADOW_EFFECT_NAMES = JsonReader.Options.of("ef");
    private static final JsonReader.Options INNER_EFFECT_NAMES = JsonReader.Options.of("nm", "v");
    private AnimatableColorValue color;
    private AnimatableFloatValue direction;
    private AnimatableFloatValue distance;
    private AnimatableFloatValue opacity;
    private AnimatableFloatValue radius;

    DropShadowEffect parse(JsonReader reader, LottieComposition composition) throws IOException {
        while (reader.hasNext()) {
            switch (reader.selectName(DROP_SHADOW_EFFECT_NAMES)) {
                case 0:
                    reader.beginArray();
                    while (reader.hasNext()) {
                        maybeParseInnerEffect(reader, composition);
                    }
                    reader.endArray();
                    break;
                default:
                    reader.skipName();
                    reader.skipValue();
                    break;
            }
        }
        if (this.color != null && this.opacity != null && this.direction != null && this.distance != null && this.radius != null) {
            return new DropShadowEffect(this.color, this.opacity, this.direction, this.distance, this.radius);
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void maybeParseInnerEffect(com.airbnb.lottie.parser.moshi.JsonReader r4, com.airbnb.lottie.LottieComposition r5) throws java.io.IOException {
        /*
            r3 = this;
            java.lang.String r0 = ""
            r4.beginObject()
        L5:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L88
            com.airbnb.lottie.parser.moshi.JsonReader$Options r1 = com.airbnb.lottie.parser.DropShadowEffectParser.INNER_EFFECT_NAMES
            int r1 = r4.selectName(r1)
            switch(r1) {
                case 0: goto L82;
                case 1: goto L1b;
                default: goto L14;
            }
        L14:
            r4.skipName()
            r4.skipValue()
            goto L5
        L1b:
            int r1 = r0.hashCode()
            r2 = 0
            switch(r1) {
                case 353103893: goto L4c;
                case 397447147: goto L42;
                case 1041377119: goto L38;
                case 1379387491: goto L2e;
                case 1383710113: goto L24;
                default: goto L23;
            }
        L23:
            goto L56
        L24:
            java.lang.String r1 = "Softness"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L23
            r1 = 4
            goto L57
        L2e:
            java.lang.String r1 = "Shadow Color"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L23
            r1 = r2
            goto L57
        L38:
            java.lang.String r1 = "Direction"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L23
            r1 = 2
            goto L57
        L42:
            java.lang.String r1 = "Opacity"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L23
            r1 = 1
            goto L57
        L4c:
            java.lang.String r1 = "Distance"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L23
            r1 = 3
            goto L57
        L56:
            r1 = -1
        L57:
            switch(r1) {
                case 0: goto L7a;
                case 1: goto L73;
                case 2: goto L6c;
                case 3: goto L65;
                case 4: goto L5e;
                default: goto L5a;
            }
        L5a:
            r4.skipValue()
            goto L81
        L5e:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r1 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r4, r5)
            r3.radius = r1
            goto L81
        L65:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r1 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r4, r5)
            r3.distance = r1
            goto L81
        L6c:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r1 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r4, r5, r2)
            r3.direction = r1
            goto L81
        L73:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r1 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r4, r5, r2)
            r3.opacity = r1
            goto L81
        L7a:
            com.airbnb.lottie.model.animatable.AnimatableColorValue r1 = com.airbnb.lottie.parser.AnimatableValueParser.parseColor(r4, r5)
            r3.color = r1
        L81:
            goto L5
        L82:
            java.lang.String r0 = r4.nextString()
            goto L5
        L88:
            r4.endObject()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.DropShadowEffectParser.maybeParseInnerEffect(com.airbnb.lottie.parser.moshi.JsonReader, com.airbnb.lottie.LottieComposition):void");
    }
}

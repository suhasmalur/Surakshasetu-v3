package okio;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes11.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    final ByteString[] byteStrings;
    final int[] trie;

    private Options(ByteString[] byteStrings, int[] trie) {
        this.byteStrings = byteStrings;
        this.trie = trie;
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x00bb, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static okio.Options of(okio.ByteString... r12) {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Options.of(okio.ByteString[]):okio.Options");
    }

    private static void buildTrieRecursive(long nodeOffset, Buffer node, int byteStringOffset, List<ByteString> byteStrings, int fromIndex, int toIndex, List<Integer> indexes) {
        int fromIndex2;
        ByteString from;
        int prefixIndex;
        int rangeEnd;
        int rangeEnd2;
        Buffer childNodes;
        int selectChoiceCount;
        int prefixIndex2;
        List<Integer> list = indexes;
        if (fromIndex >= toIndex) {
            throw new AssertionError();
        }
        for (int i = fromIndex; i < toIndex; i++) {
            if (byteStrings.get(i).size() < byteStringOffset) {
                throw new AssertionError();
            }
        }
        ByteString from2 = byteStrings.get(fromIndex);
        ByteString to = byteStrings.get(toIndex - 1);
        if (byteStringOffset != from2.size()) {
            fromIndex2 = fromIndex;
            from = from2;
            prefixIndex = -1;
        } else {
            int prefixIndex3 = list.get(fromIndex).intValue();
            int fromIndex3 = fromIndex + 1;
            fromIndex2 = fromIndex3;
            from = byteStrings.get(fromIndex3);
            prefixIndex = prefixIndex3;
        }
        if (from.getByte(byteStringOffset) != to.getByte(byteStringOffset)) {
            int selectChoiceCount2 = 1;
            for (int i2 = fromIndex2 + 1; i2 < toIndex; i2++) {
                if (byteStrings.get(i2 - 1).getByte(byteStringOffset) != byteStrings.get(i2).getByte(byteStringOffset)) {
                    selectChoiceCount2++;
                }
            }
            long childNodesOffset = nodeOffset + ((long) intCount(node)) + 2 + ((long) (selectChoiceCount2 * 2));
            node.writeInt(selectChoiceCount2);
            node.writeInt(prefixIndex);
            for (int i3 = fromIndex2; i3 < toIndex; i3++) {
                byte rangeByte = byteStrings.get(i3).getByte(byteStringOffset);
                if (i3 == fromIndex2 || rangeByte != byteStrings.get(i3 - 1).getByte(byteStringOffset)) {
                    node.writeInt(rangeByte & 255);
                }
            }
            Buffer childNodes2 = new Buffer();
            int rangeStart = fromIndex2;
            while (rangeStart < toIndex) {
                byte rangeByte2 = byteStrings.get(rangeStart).getByte(byteStringOffset);
                int i4 = rangeStart + 1;
                while (true) {
                    if (i4 >= toIndex) {
                        rangeEnd = toIndex;
                        break;
                    } else if (rangeByte2 == byteStrings.get(i4).getByte(byteStringOffset)) {
                        i4++;
                    } else {
                        int rangeEnd3 = i4;
                        rangeEnd = rangeEnd3;
                        break;
                    }
                }
                int rangeEnd4 = rangeStart + 1;
                if (rangeEnd4 != rangeEnd || byteStringOffset + 1 != byteStrings.get(rangeStart).size()) {
                    node.writeInt((int) ((childNodesOffset + ((long) intCount(childNodes2))) * (-1)));
                    int rangeStart2 = byteStringOffset + 1;
                    rangeEnd2 = rangeEnd;
                    childNodes = childNodes2;
                    selectChoiceCount = selectChoiceCount2;
                    prefixIndex2 = prefixIndex;
                    buildTrieRecursive(childNodesOffset, childNodes2, rangeStart2, byteStrings, rangeStart, rangeEnd2, indexes);
                } else {
                    node.writeInt(list.get(rangeStart).intValue());
                    rangeEnd2 = rangeEnd;
                    childNodes = childNodes2;
                    selectChoiceCount = selectChoiceCount2;
                    prefixIndex2 = prefixIndex;
                }
                rangeStart = rangeEnd2;
                childNodes2 = childNodes;
                prefixIndex = prefixIndex2;
                selectChoiceCount2 = selectChoiceCount;
                list = indexes;
            }
            Buffer childNodes3 = childNodes2;
            node.write(childNodes3, childNodes3.size());
            return;
        }
        int prefixIndex4 = prefixIndex;
        int max = Math.min(from.size(), to.size());
        int scanByteCount = 0;
        for (int i5 = byteStringOffset; i5 < max && from.getByte(i5) == to.getByte(i5); i5++) {
            scanByteCount++;
        }
        long childNodesOffset2 = nodeOffset + ((long) intCount(node)) + 2 + ((long) scanByteCount) + 1;
        node.writeInt(-scanByteCount);
        node.writeInt(prefixIndex4);
        for (int i6 = byteStringOffset; i6 < byteStringOffset + scanByteCount; i6++) {
            node.writeInt(from.getByte(i6) & 255);
        }
        int i7 = fromIndex2 + 1;
        if (i7 != toIndex) {
            Buffer childNodes4 = new Buffer();
            node.writeInt((int) ((childNodesOffset2 + ((long) intCount(childNodes4))) * (-1)));
            buildTrieRecursive(childNodesOffset2, childNodes4, byteStringOffset + scanByteCount, byteStrings, fromIndex2, toIndex, indexes);
            node.write(childNodes4, childNodes4.size());
            return;
        }
        if (byteStringOffset + scanByteCount == byteStrings.get(fromIndex2).size()) {
            node.writeInt(indexes.get(fromIndex2).intValue());
            return;
        }
        throw new AssertionError();
    }

    @Override // java.util.AbstractList, java.util.List
    public ByteString get(int i) {
        return this.byteStrings[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.byteStrings.length;
    }

    private static int intCount(Buffer trieBytes) {
        return (int) (trieBytes.size() / 4);
    }
}

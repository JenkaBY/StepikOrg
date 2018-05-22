package org.stepik.java.adaptive.three;


abstract class UniversalInteger implements Comparable<Long> //, Comparable<Integer>, Comparable<Byte> {
{
    // This wouldn't work: this code tries to implement multiple interfaces with same erasure
    @Override
    public int compareTo(Long o) {
        return 0;
    }
}
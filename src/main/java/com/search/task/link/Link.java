package com.search.task.link;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Class fo link.
 * Contains position for particular searcher
 * or positions for several searcher
 * Created by IPahomov on 10.07.2016.
 */
public class Link implements Comparable<Link>, Serializable {
    private String url;
    private Integer position;
    private int[] positions;
    private double average;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public int[] getPositions() {
        return positions;
    }

    public void setPositions(int[] positions) {
        this.positions = positions;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        return !(url != null ? !url.equals(link.url) : link.url != null);
    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Link{" +
                "url='" + url + '\'' +
                ", position=" + position +
                ", positions=" + Arrays.toString(positions) +
                ", average=" + average +
                '}';
    }

    public int compareTo(Link o) {
        int result = this.getUrl().compareTo(o.getUrl());
        if (result == 0) {
            return result;
        } else {
            return Double.compare(this.getAverage(), o.getAverage());
        }

    }
}

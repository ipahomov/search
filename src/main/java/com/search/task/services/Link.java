package com.search.task.services;

import java.util.Arrays;

/**
 * Created by IPahomov on 10.07.2016.
 */
public class Link {
    private String title;
    private String url;
    private String searcherName;
    private Integer position;
    private int[] positions;
    private double average;

    public Link(String searcherName){
        this.searcherName = searcherName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSearcherName() {
        return searcherName;
    }

    public void setSearcherName(String searcherName) {
        this.searcherName = searcherName;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }


    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int[] getPositions() {
        return positions;
    }

    public void setPositions(int[] positions) {
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (Double.compare(link.average, average) != 0) return false;
        if (title != null ? !title.equals(link.title) : link.title != null) return false;
        if (url != null ? !url.equals(link.url) : link.url != null) return false;
        if (searcherName != null ? !searcherName.equals(link.searcherName) : link.searcherName != null) return false;
        if (position != null ? !position.equals(link.position) : link.position != null) return false;
        return Arrays.equals(positions, link.positions);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (searcherName != null ? searcherName.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(positions);
        temp = Double.doubleToLongBits(average);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Link{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", searcherName='" + searcherName + '\'' +
                ", position=" + position +
                ", positions=" + Arrays.toString(positions) +
                ", average=" + average +
                '}';
    }
}

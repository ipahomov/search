package com.search.task.searchers;

/**
 * Abstract class for build searchers
 * with own parameters.
 * Created by IPahomov on 12.07.2016.
 */
public abstract class SearcherBuilder {
    protected Searcher searcher;

    public Searcher getSearcher() {
        return searcher;
    }

    public void createNewSearcher() {
        searcher = new Searcher();
    }

    /**
     * Set searcher name
     */
    public abstract void buildName();

    /**
     * Set searcher address
     */
    public abstract void buildAddress();

    /**
     * Set searcher tag for finding hrefs
     */
    public abstract void buildTag();

    /**
     * Set searcher rating
     */
    public abstract void buildRating();

}

package org.happybean.lucene.query;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.FSDirectory;

/**
 * @author wgt
 * @date 2018-11-16
 * @description
 **/
public class TxtFileSearcher {
    public static void main(String[] args) throws Exception {
        String queryStr = "test";
        //索引目录
        File indexDir = new File("/opt/lucene/index");
        FSDirectory directory = FSDirectory.getDirectory(indexDir, false);
        IndexSearcher searcher = new IndexSearcher(directory);
        if (!indexDir.exists()) {
            System.out.println("The Lucene index is not exist");
            return;
        }
        Term term = new Term("contents", queryStr.toLowerCase());
        TermQuery luceneQuery = new TermQuery(term);
        Hits hits = searcher.search(luceneQuery);
        for (int i = 0; i < hits.length(); i++) {
            Document document = hits.doc(i);
            System.out.println("File: " + document.get("path"));
        }
    }
}

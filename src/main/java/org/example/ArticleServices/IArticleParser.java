package org.example.ArticleServices;

import org.example.Models.Article;

import java.io.File;
import java.io.IOException;

public interface IArticleParser {
    File parse(Article article) throws IOException;
}

package org.realisticdreams.utility;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class QuotesLoader {
    private Map<String, List<String>> quotes;

    public QuotesLoader() {
        loadQuotes();
    }

    private void loadQuotes() {
        Yaml yaml = new Yaml();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("quotes.yml")) {
            quotes = yaml.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getQuotes(String category) {
        return quotes.getOrDefault(category, Collections.emptyList());
    }
}

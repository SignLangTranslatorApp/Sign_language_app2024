
package mquinn.sign_language.customView;



import java.util.List;

import mquinn.sign_language.tfLite.Classifier;

public interface ResultsView {
    public void setResults(final List<Classifier.Recognition> results);
}

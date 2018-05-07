package sample.com.flickrclient.util.function;

import sample.com.flickrclient.data.model.Size;
import sample.com.flickrclient.data.model.Sizes;
import io.reactivex.functions.Function;

public class ImageUrlProvider implements Function<Sizes, String> {

    private static final String DEFAULT_SIZE = "Medium";

    @Override
    public String apply(Sizes sizes) throws Exception {
        if (sizes == null) {
            return "";
        }

        Size size = null;
        for (int i = 0; i < sizes.getSize().size(); i++) {
            Size s = sizes.getSize().get(i);
            if (DEFAULT_SIZE.equals(s.getLabel())) {
                size = s;
                break;
            }
        }

        return size != null ? size.getSource() : "";
    }
}

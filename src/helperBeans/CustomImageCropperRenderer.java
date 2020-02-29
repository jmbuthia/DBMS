/*package helperBeans;

import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.imagecropper.ImageCropper;
import org.primefaces.component.imagecropper.ImageCropperRenderer;
import org.primefaces.model.CroppedImage;

@ManagedBean
@ApplicationScoped
public class CustomImageCropperRenderer extends ImageCropperRenderer {

    @Override
    protected void encodeScript(FacesContext context, ImageCropper cropper) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String clientId = cropper.getClientId(context);
        writer.startElement("script", null);
        writer.writeAttribute("language", "Javascript", null);
        StringBuilder script = new StringBuilder();
        script.append("jQuery(function($){$('#")
                .append(clientId)
                .append("_image').Jcrop({");
        if (cropper.getMinSize() != null) {
            script.append("minSize:[").append(cropper.getMinSize()).append("]");
        }
        if (cropper.getMaxSize() != null) {
            script.append("maxSize:").append(cropper.getMaxSize());
        }
        script.append(",aspectRatio:").append(cropper.getAspectRatio());
        Object value = cropper.getValue();
        String select = null;
        if (value != null) {
            CroppedImage croppedImage = (CroppedImage) value;

            int x = croppedImage.getLeft();
            int y = croppedImage.getTop();
            int x2 = x + croppedImage.getWidth();
            int y2 = y + croppedImage.getHeight();

            select = "[" + x + "," + y + "," + x2 + "," + y2 + "]";
        } else if (cropper.getInitialCoords() != null) {
            select = "[" + cropper.getInitialCoords() + "]";
        }
        script.append(",setSelect:")
                .append(select)
                .append(",onChange: updateCoords")
                .append(",onSelect: updateCoords")
                .append(",onRelease: updateCoords")
                .append("});")
                .append("function updateCoords(c){$('#")
                .append(clientId)
                .append("_coords').val(c.x + \"_\" + c.y + \"_\" + c.w + \"_\" + c.h);};});");

        writer.write(script.toString());
        writer.endElement("script");
    }
}*/
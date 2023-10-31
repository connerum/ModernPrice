package com.connerum.modernprice.Model;
import com.finium.core.drivers.zebra.model.ZebraLabel;
import com.finium.core.drivers.zebra.model.element.ZebraBarCode39;
import com.finium.core.drivers.zebra.model.element.ZebraText;
import com.finium.core.drivers.zebra.zpl.enums.ZebraFont;
import com.finium.core.drivers.zebra.zpl.support.ZplUtils;
public class Zebra {
    public static void main(String[] args) {
        ZebraLabel zebraLabel = new ZebraLabel(912, 912);
        zebraLabel.setDefaultZebraFont(ZebraFont.ZEBRA_ZERO);

        zebraLabel.addElement(new ZebraText(10, 84, "Product:", 14));
        zebraLabel.addElement(new ZebraText(395, 85, "Camera", 14));

        zebraLabel.addElement(new ZebraText(10, 161, "CA201212AA", 14));

        //Add Code Bar 39
        zebraLabel.addElement(new ZebraBarCode39(10, 297, "CA201212AA", 118, 2, 2));

        zebraLabel.addElement(new ZebraText(10, 365, "Qté:", 11));
        zebraLabel.addElement(new ZebraText(180, 365, "3", 11));
        zebraLabel.addElement(new ZebraText(317, 365, "QA", 11));

        zebraLabel.addElement(new ZebraText(10, 520, "Ref log:", 11));
        zebraLabel.addElement(new ZebraText(180, 520, "0035", 11));
        zebraLabel.addElement(new ZebraText(10, 596, "Ref client:", 11));
        zebraLabel.addElement(new ZebraText(180, 599, "1234", 11));

        ZplUtils.printZpl(zebraLabel, ip, port);
    }
}

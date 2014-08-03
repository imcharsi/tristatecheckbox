package com.github.imcharsi.vaadin.tristatecheckbox.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.vaadin.client.ui.VCheckBox;

/**
 * Created by KangWoo,Lee on 14. 8. 2.
 * copied from VCheckBox
 */
public class TriStateCheckBoxWidget extends VCheckBox {
    private int triState = 1;

    public int getTriState() {
        return triState;
    }

    public void setTriState(int triState) {
        this.triState = triState;
    }

    public TriStateCheckBoxWidget() {
        super();
        addClickHandler(new ListenerOne());
    }

    private class ListenerOne implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            setTriState((getTriState() + 1) % 3);
            checkBoxUpdate();
        }
    }

    public void checkBoxUpdate() {
        if (getTriState() == 0) {
            getElement().getFirstChildElement().setPropertyString("checked", "checked");
            getElement().getFirstChildElement().setPropertyBoolean("indeterminate", false);
        } else if (getTriState() == 1) {
            getElement().getFirstChildElement().setPropertyString("checked", null);
            getElement().getFirstChildElement().setPropertyBoolean("indeterminate", false);
        } else if (getTriState() == 2) {
            getElement().getFirstChildElement().setPropertyString("checked", "checked");
            getElement().getFirstChildElement().setPropertyBoolean("indeterminate", true);
        }
    }
}

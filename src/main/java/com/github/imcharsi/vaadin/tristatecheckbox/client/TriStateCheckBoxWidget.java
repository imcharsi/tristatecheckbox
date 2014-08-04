package com.github.imcharsi.vaadin.tristatecheckbox.client;

/*
 * #%L
 * tristatecheckbox
 * %%
 * Copyright (C) 2014 KangWoo, Lee
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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

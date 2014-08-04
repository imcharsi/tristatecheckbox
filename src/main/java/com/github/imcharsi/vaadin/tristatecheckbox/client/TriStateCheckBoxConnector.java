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

import com.github.imcharsi.vaadin.tristatecheckbox.TriStateCheckBox;
import com.github.imcharsi.vaadin.tristatecheckbox.client.shared.TriStateCheckBoxServerRpc;
import com.github.imcharsi.vaadin.tristatecheckbox.client.shared.TriStateCheckBoxState;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.checkbox.CheckBoxConnector;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.Connect;

/**
 * Created by KangWoo,Lee on 14. 8. 2.
 * copied from CheckBoxConnector
 */
@Connect(TriStateCheckBox.class)
public class TriStateCheckBoxConnector extends CheckBoxConnector {
    @Override
    public TriStateCheckBoxState getState() {
        return (TriStateCheckBoxState) super.getState();
    }

    @Override
    public TriStateCheckBoxWidget getWidget() {
        return (TriStateCheckBoxWidget) super.getWidget();
    }

    @Override
    protected TriStateCheckBoxWidget createWidget() {
        return GWT.create(TriStateCheckBoxWidget.class);
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        getWidget().setTriState(getState().triState);
        getWidget().checkBoxUpdate();
    }

    @Override
    public void onClick(ClickEvent event) {
        if (!isEnabled()) {
            return;
        }
        if (getState().triState != getWidget().getTriState()) {
            getState().triState = getWidget().getTriState();
            MouseEventDetails details = MouseEventDetailsBuilder.buildMouseEventDetails(event.getNativeEvent(), getWidget().getElement());
            getRpcProxy(TriStateCheckBoxServerRpc.class).setChecked(getState().triState, details);
        }
    }
}

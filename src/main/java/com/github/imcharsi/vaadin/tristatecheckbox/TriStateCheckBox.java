package com.github.imcharsi.vaadin.tristatecheckbox;

import com.github.imcharsi.vaadin.tristatecheckbox.client.shared.TriStateCheckBoxServerRpc;
import com.github.imcharsi.vaadin.tristatecheckbox.client.shared.TriStateCheckBoxState;
import com.vaadin.data.Property;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.CheckBox;
import org.json.JSONException;

/**
 * Created by KangWoo,Lee on 14. 8. 2.
 * copied from CheckBox
 */
public class TriStateCheckBox extends CheckBox {
    private TriStateCheckBoxServerRpc rpc = new TriStateCheckBoxServerRpc() {
        @Override
        public void setChecked(int triState, MouseEventDetails mouseEventDetails) {
            if (isReadOnly()) {
                return;
            }
            try {
                getUI().getConnectorTracker().getDiffState(TriStateCheckBox.this).put("triState", triState);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            final Boolean oldValue = getValue();
            final Boolean newValue = convert(triState);

            if (!compare(oldValue, newValue)) {
                setValue(newValue);
            }
            getState().triState = triState;
        }

        private boolean compare(Boolean oldValue, Boolean newValue) {
            return (oldValue == newValue);
        }
    };

    public TriStateCheckBox() {
        super();
        registerRpc(rpc);
    }

    public TriStateCheckBox(String caption) {
        this();
        setCaption(caption);
    }

    public TriStateCheckBox(String caption, boolean initialState) {
        this(caption);
        setValue(initialState);
    }

    public TriStateCheckBox(String caption, Property<?> dataSource) {
        this(caption);
        setPropertyDataSource(dataSource);
    }

    @Override
    protected void setInternalValue(Boolean newValue) {
        super.setInternalValue(newValue);
        getState().triState = reverseConvert(newValue);
    }

    @Override
    protected TriStateCheckBoxState getState() {
        return (TriStateCheckBoxState) super.getState();
    }

    private Boolean convert(int triState) {
        if (triState % 3 == 0)
            return true;
        else if (triState % 3 == 1)
            return false;
        else return null;
    }

    private int reverseConvert(Boolean value) {
        if (value == null) return 2;
        else if (value == true) return 0;
        else return 1;
    }
}

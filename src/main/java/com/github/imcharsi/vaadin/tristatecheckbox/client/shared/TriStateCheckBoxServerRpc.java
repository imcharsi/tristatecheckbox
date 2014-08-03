package com.github.imcharsi.vaadin.tristatecheckbox.client.shared;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.ServerRpc;

/**
 * Created by KangWoo,Lee on 14. 8. 2.
 */
public interface TriStateCheckBoxServerRpc extends ServerRpc {
    public void setChecked(int triState, MouseEventDetails mouseEventDetails);
}

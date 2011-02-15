/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mbgnb.core.actions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.mbgnb.core.filetype.*;

public final class Generate implements ActionListener {

    private final nbftDataObject context;

    public Generate(nbftDataObject context) {
        this.context = context;
    }

    public void actionPerformed(ActionEvent ev) {
        // TODO use context
    }
}

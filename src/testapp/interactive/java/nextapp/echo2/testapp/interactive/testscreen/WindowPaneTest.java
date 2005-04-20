/* 
 * This file is part of the Echo Web Application Framework (hereinafter "Echo").
 * Copyright (C) 2002-2005 NextApp, Inc.
 *
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 */

package nextapp.echo2.testapp.interactive.testscreen;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.ContentPane;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.SplitPane;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.WindowPane;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.layout.SplitPaneLayoutData;
import nextapp.echo2.testapp.interactive.ButtonRow;
import nextapp.echo2.testapp.interactive.InteractiveApp;
import nextapp.echo2.testapp.interactive.StyleUtil;
import nextapp.echo2.testapp.interactive.Styles;

public class WindowPaneTest extends SplitPane {
    
    private int windowNumber = 0;
    
    private int nextPosition = 0;
    
    private class WindowTestControls extends ButtonRow {
        
        private WindowTestControls(String targetName, final ContentPane targetContentPane) {
            add(new Label(targetName));
            addButton("Add Simple Window", new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    final WindowPane windowPane = new WindowPane();
                    positionWindowPane(windowPane);
                    windowPane.setTitle("Simple Window #" + windowNumber++);
                    targetContentPane.add(windowPane);
                    
                    Row windowPaneRow = new Row();
                    windowPane.add(windowPaneRow);
                    windowPaneRow.add(new Label("First Name:"));
                    windowPaneRow.add(new TextField());
                    windowPaneRow.add(new Label("Last Name:"));
                    windowPaneRow.add(new TextField());
                }
            });
    
            addButton("Add SplitPane Window", new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    final WindowPane windowPane = new WindowPane();
                    positionWindowPane(windowPane);
                    targetContentPane.add(windowPane);
                    windowPane.setTitle("SplitPane Window #" + windowNumber++);
                    windowPane.setTitleInsets(new Insets(10, 5));
                    windowPane.setBorder(new Border(new Extent(2, Extent.PX), new Color(0x0f0f2f), Border.STYLE_GROOVE));
                    windowPane.setTitleBackground(new Color(0x2f2f4f));
                    windowPane.setWidth(new Extent(500, Extent.PX));
                    windowPane.setHeight(new Extent(280, Extent.PX));
                    SplitPane splitPane = new SplitPane(SplitPane.ORIENTATION_VERTICAL, new Extent(240, Extent.PX));
                    SplitPaneLayoutData splitPaneLayoutData;
                    
                    Label contentLabel = new Label(StyleUtil.QUASI_LATIN_TEXT_1);
                    splitPaneLayoutData = new SplitPaneLayoutData();
                    splitPaneLayoutData.setBackground(new Color(0xefefff));
                    contentLabel.setLayoutData(splitPaneLayoutData);
                    splitPane.add(contentLabel);
                    
                    //BUGBUG. attempt to center this button failed.
                    Button okButton = new Button("Ok");
                    okButton.addActionListener(new ActionListener() {
                        /**
                         * @see nextapp.echo2.app.event.ActionListener#actionPerformed(nextapp.echo2.app.event.ActionEvent)
                         */
                        public void actionPerformed(ActionEvent e) {
                            windowPane.getParent().remove(windowPane);
                        }
                    });
                    splitPaneLayoutData = new SplitPaneLayoutData();
                    splitPaneLayoutData.setBackground(new Color(0x5f5f9f));
                    splitPaneLayoutData.setInsets(new Insets(8));
                    splitPaneLayoutData.setOverflow(SplitPaneLayoutData.OVERFLOW_HIDDEN);
                    okButton.setLayoutData(splitPaneLayoutData);
                    okButton.setWidth(new Extent(100));
                    okButton.setStyleName(Styles.DEFAULT_STYLE_NAME);
                    splitPane.add(okButton);
                    
                    windowPane.add(splitPane);
                }
            });
    
            addButton("Add Multiple SplitPane Window", new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    final WindowPane windowPane = new WindowPane();
                    positionWindowPane(windowPane);
                    targetContentPane.add(windowPane);
                    windowPane.setTitle("Multiple SplitPane Window #" + windowNumber++);
                    windowPane.setBorder(new Border(new Extent(3, Extent.PX), new Color(0x0f0f2f), Border.STYLE_OUTSET));
                    windowPane.setTitleBackground(new Color(0x2f2f4f));
                    windowPane.setWidth(new Extent(700, Extent.PX));
                    windowPane.setWidth(new Extent(500, Extent.PX));
                    
                    SplitPane splitPane1 = new SplitPane(SplitPane.ORIENTATION_HORIZONTAL, new Extent(100, Extent.PX));
                    splitPane1.setResizable(true);
                    SplitPaneLayoutData splitPaneLayoutData;
                    
                    Label label;
                    
                    label = new Label(StyleUtil.QUASI_LATIN_TEXT_1);
                    splitPaneLayoutData = new SplitPaneLayoutData();
                    splitPaneLayoutData.setBackground(new Color(0x3fbf5f));
                    label.setLayoutData(splitPaneLayoutData);
                    splitPane1.add(label);

                    SplitPane splitPane2 = new SplitPane(SplitPane.ORIENTATION_VERTICAL, new Extent(200, Extent.PX));
                    splitPane2.setResizable(true);
                    
                    SplitPane splitPane3 = new SplitPane(SplitPane.ORIENTATION_HORIZONTAL, new Extent(200, Extent.PX));
                    splitPane3.setResizable(true);
                    splitPane2.add(splitPane3);
                    
                    SplitPane splitPane4 = new SplitPane(SplitPane.ORIENTATION_HORIZONTAL, new Extent(300, Extent.PX));
                    splitPane4.setResizable(true);
                    splitPane2.add(splitPane4);
                    
                    label = new Label(StyleUtil.QUASI_LATIN_TEXT_1);
                    splitPaneLayoutData = new SplitPaneLayoutData();
                    splitPaneLayoutData.setBackground(new Color(0x5f3fbf));
                    label.setLayoutData(splitPaneLayoutData);
                    splitPane3.add(label);
                    
                    label = new Label(StyleUtil.QUASI_LATIN_TEXT_1);
                    splitPaneLayoutData = new SplitPaneLayoutData();
                    splitPaneLayoutData.setBackground(new Color(0x3f5fbf));
                    label.setLayoutData(splitPaneLayoutData);
                    splitPane3.add(label);
                    
                    label = new Label(StyleUtil.QUASI_LATIN_TEXT_1);
                    splitPaneLayoutData = new SplitPaneLayoutData();
                    splitPaneLayoutData.setBackground(new Color(0xbf5f3f));
                    label.setLayoutData(splitPaneLayoutData);
                    splitPane4.add(label);
                    
                    label = new Label(StyleUtil.QUASI_LATIN_TEXT_1);
                    splitPaneLayoutData = new SplitPaneLayoutData();
                    splitPaneLayoutData.setBackground(new Color(0xbf3f5f));
                    label.setLayoutData(splitPaneLayoutData);
                    splitPane4.add(label);
    
                    splitPane1.add(splitPane2);
                    
                    windowPane.add(splitPane1);
                }
            });
        }
    }
    
    public WindowPaneTest() {
        super(SplitPane.ORIENTATION_HORIZONTAL, new Extent(250, Extent.PX));
        setResizable(true);
        
        ButtonRow controlsRow = new ButtonRow();
        controlsRow.setCellSpacing(new Extent(5));
        controlsRow.setStyleName(Styles.TEST_CONTROLS_ROW_STYLE_NAME);
        add(controlsRow);
        
        final ContentPane contentPane = new ContentPane();
        add(contentPane);

        WindowTestControls windowTestControls;
        windowTestControls = new WindowTestControls("Root Level", InteractiveApp.getApp().getMainWindow().getContent());
        controlsRow.add(windowTestControls);
        windowTestControls = new WindowTestControls("Embedded", contentPane);
        controlsRow.add(windowTestControls);
    }
    
    private void positionWindowPane(WindowPane windowPane) {
        Extent positionExtent = new Extent(nextPosition, Extent.PX);
        windowPane.setPositionX(positionExtent);
        windowPane.setPositionY(positionExtent);
        nextPosition += 20;
        if (nextPosition > 200) {
            nextPosition = 0;
        }
    }
}
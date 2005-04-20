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

package nextapp.echo2.testapp.webrender;

import java.io.IOException;

import nextapp.echo2.webrender.output.BaseHtmlDocument;
import nextapp.echo2.webrender.server.Connection;
import nextapp.echo2.webrender.server.ContentType;
import nextapp.echo2.webrender.server.Service;
import nextapp.echo2.webrender.server.WebRenderServlet;

/**
 * Service performed when the user first visits an application.
 */
public class NewInstanceService implements Service {

    /**
     * @see nextapp.echo2.webrender.server.Service#getId()
     */
    public String getId() {
        return WebRenderServlet.SERVICE_ID_NEW_INSTANCE;
    }
    
    /**
     * @see nextapp.echo2.webrender.server.Service#getVersion()
     */
    public int getVersion() {
        return DO_NOT_CACHE;
    }

    /**
     * @see nextapp.echo2.webrender.server.Service#service(nextapp.echo2.webrender.server.Connection)
     */
    public void service(Connection conn) throws IOException {
        conn.setContentType(ContentType.TEXT_HTML);
        BaseHtmlDocument baseDoc = new BaseHtmlDocument("c_0");
        baseDoc.getBodyElement().appendChild(baseDoc.getDocument().createTextNode("New Instance"));
        baseDoc.render(conn.getWriter());
    }
}
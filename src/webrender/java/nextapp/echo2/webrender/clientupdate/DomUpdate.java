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

package nextapp.echo2.webrender.clientupdate;

import org.w3c.dom.Element;

/**
 * A utility class to add <code>EchoDomUpdate</code> message parts to the 
 * <code>ServerMessage</code>.  <code>EchoDomUpdate</code> message parts
 * are used to directly update the client DOM with HTML code generated on
 * the server.
 */
public class DomUpdate {
    
    private static final String MESSAGE_PART_NAME = "EchoDomUpdate";
    private static final String XHTML_NAMESPACE = "http://www.w3.org/1999/xhtml";
    
    /**
     * Creates a <code>domadd</code> operation to append HTML content to the 
     * end of the element identified by <code>parentId</code>.
     * 
     * @param serverMessage the outgoing <code>ServerMessage</code>
     * @param parentId the id of the element the HTML code will be appended to
     * @return a content element to which the server-generated HTML may be 
     *         added
     */
    public static Element createDomAdd(ServerMessage serverMessage, String parentId) {
        return createDomAdd(serverMessage, parentId, null);
    }

    /**
     * Creates a <code>domadd</code> operation to insert HTML content int the 
     * element identified by <code>parentId</code>.
     * 
     * @param serverMessage the outgoing <code>ServerMessage</code>
     * @param parentId the id of the element into which the HTML code will be 
     *        inserted
     * @param siblingId The id of the element which the content will be inserted
     *        <strong>before</strong> (this element must be an immediate child
     *        of the element specified by <code>parentId</code>)
     * @return a content element to which the server-generated HTML may be 
     *         added.
     */
    public static Element createDomAdd(ServerMessage serverMessage, String parentId, String siblingId) {
        Element domAddElement = serverMessage.appendPartDirective(ServerMessage.GROUP_ID_UPDATE, 
                MESSAGE_PART_NAME, "domadd");
        domAddElement.setAttribute("parentid", parentId);
        if (siblingId != null) {
            domAddElement.setAttribute("siblingid", siblingId);
        }
        Element contentElement = domAddElement.getOwnerDocument().createElementNS(XHTML_NAMESPACE, "content");
        contentElement.setAttribute("xmlns", XHTML_NAMESPACE);
        domAddElement.appendChild(contentElement);
        return contentElement;
    }
    
    /**
     * Creates a <code>domremove</code> operation to remove the HTML element 
     * identified by <code>targetId</code> from the client DOM.
     * 
     * @param serverMessage the outgoing <code>ServerMessage</code>
     * @param targetId the id of the element to remove
     */
    public static void createDomRemove(ServerMessage serverMessage, String targetId) {
        Element domRemoveElement = serverMessage.appendPartDirective(ServerMessage.GROUP_ID_REMOVE, 
                MESSAGE_PART_NAME, "domremove");
        domRemoveElement.setAttribute("targetid", targetId);
    }

    /**
     * Creates a <code>domremove</code> operation to remove all child elements
     * of the element identified by <code>targetId</code> from the client DOM.
     * 
     * @param serverMessage the outgoing <code>ServerMessage</code>
     * @param targetId the id of the element whose children ware to be removed
     */
    public static void createDomRemoveChildren(ServerMessage serverMessage, String targetId) {
        Element domRemoveElement = serverMessage.appendPartDirective(ServerMessage.GROUP_ID_REMOVE, 
                MESSAGE_PART_NAME, "domremovechildren");
        domRemoveElement.setAttribute("targetid", targetId);
    }

    /**
     * Creates a <code>attributeupdate</code> operation to update an 
     * element attribute of the element identified by <code>targetId</code>
     * in the client DOM.
     * 
     * @param serverMessage the outgoing <code>ServerMessage</code>
     * @param targetId the id of the element whose attribute is to be updated
     * @param attributeName the name of the attribute to update
     * @param attributeValue the new value of the attribute
     */
    public static void updateAttribute(ServerMessage serverMessage, String targetId, String attributeName, 
            String attributeValue) {
        //BUGUG. support nulls for deletion.
        Element element = serverMessage.appendPartDirective(ServerMessage.GROUP_ID_UPDATE, 
                MESSAGE_PART_NAME, "attributeupdate");
        element.setAttribute("targetid", targetId);
        element.setAttribute("name", attributeName);
        element.setAttribute("value", attributeValue);
    }
    
    /**
     * Creates a <code>styleupdate</code> operation to update a CSS style 
     * attribute of the element identified by <code>targetId</code> in the
     * client DOM.
     * 
     * @param serverMessage the outgoing <code>ServerMessage</code>
     * @param targetId the id of the element whose <strong>style</strong> 
     *        attribute is to be updated
     * @param attributeName the name of the <strong>style</strong> attribute
     * @param attributeValue the new value of the <strong>style</strong> 
     *        attribute
     */
    public static void updateStyle(ServerMessage serverMessage, String targetId, String attributeName, 
            String attributeValue) {
        //BUGUG. support nulls for deletion.
        Element element = serverMessage.appendPartDirective(ServerMessage.GROUP_ID_UPDATE, 
                MESSAGE_PART_NAME, "styleupdate");
        element.setAttribute("targetid", targetId);
        element.setAttribute("name", attributeName);
        element.setAttribute("value", attributeValue);
    }
}
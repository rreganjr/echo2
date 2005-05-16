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

package nextapp.echo2.webcontainer.test;

import nextapp.echo2.webcontainer.util.IdTable;
import junit.framework.TestCase;

/**
 * 
 */
public class IdTableTest extends TestCase {

    private static class TestObject { }
    
    /**
     * Tests to ensure references are released.  This test relies on
     * <code>System.gc()</code> acutally causing weak references to
     * be collected.  It is not technically safe to rely on this,
     * thus this test may randomly fail. 
     */
    public void testReferenceRelease() {
        IdTable idManager = new IdTable();
        TestObject testObject = new TestObject();
        String id = idManager.getId(testObject);
        assertNotNull(idManager.getObject(id));
        assertNotNull(idManager.getId(testObject));
        testObject = null;
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        assertNull(idManager.getObject(id));
        assertNull(idManager.getObject(id));
    }
}
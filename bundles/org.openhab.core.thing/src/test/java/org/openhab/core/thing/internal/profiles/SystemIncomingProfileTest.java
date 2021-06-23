/**
 * Copyright (c) 2010-2021 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.core.thing.internal.profiles;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.thing.profiles.ProfileCallback;

/**
 *
 * @author James Hewitt - Initial contribution
 */
@ExtendWith(MockitoExtension.class)
public class SystemIncomingProfileTest {

    private @Mock ProfileCallback mockCallback;

    @Test
    public void testOnCommand() {
        SystemIncomingProfile profile = new SystemIncomingProfile(mockCallback);

        profile.onCommandFromItem(OnOffType.ON);

        verifyNoMoreInteractions(mockCallback);
    }

    @Test
    public void testOnUpdate() {
        SystemIncomingProfile profile = new SystemIncomingProfile(mockCallback);
        profile.onStateUpdateFromItem(OnOffType.ON);

        verifyNoMoreInteractions(mockCallback);
    }

    @Test
    public void testStateUpdated() {
        SystemIncomingProfile profile = new SystemIncomingProfile(mockCallback);
        profile.onStateUpdateFromHandler(OnOffType.ON);

        verify(mockCallback).sendUpdate(eq(OnOffType.ON));
        verifyNoMoreInteractions(mockCallback);
    }

    @Test
    public void testPostCommand() {
        SystemIncomingProfile profile = new SystemIncomingProfile(mockCallback);
        profile.onCommandFromHandler(OnOffType.ON);

        verify(mockCallback).sendCommand(eq(OnOffType.ON));
        verifyNoMoreInteractions(mockCallback);
    }
}
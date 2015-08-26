/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.platform.cache.store;

import org.apache.ignite.internal.portable.*;
import org.apache.ignite.internal.processors.platform.*;
import org.apache.ignite.internal.processors.platform.memory.*;

/**
 * Platform cache store callback.
 */
public abstract class PlatformCacheStoreCallback {
    /** Context. */
    protected final PlatformContext ctx;

    /**
     * Constructor.
     *
     * @param ctx Context.
     */
    protected PlatformCacheStoreCallback(PlatformContext ctx) {
        this.ctx = ctx;
    }

    /**
     * Invoke the callback.
     *
     * @param memPtr Memory pointer.
     */
    public void invoke(long memPtr) {
        if (memPtr > 0) {
            try (PlatformMemory mem = ctx.memory().get(memPtr)) {
                PortableRawReaderEx reader = ctx.reader(mem);

                invoke0(reader);
            }
        }
    }

    /**
     * Internal invoke routine.
     *
     * @param reader Reader.
     */
    protected abstract void invoke0(PortableRawReaderEx reader);
}

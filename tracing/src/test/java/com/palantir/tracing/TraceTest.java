/*
 * (c) Copyright 2018 Palantir Technologies Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.palantir.tracing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.palantir.tracing.api.OpenSpan;
import com.palantir.tracing.api.SpanType;
import org.junit.Test;

public final class TraceTest {

    @Test
    public void constructTrace_emptyTraceId() {
        assertThatThrownBy(() -> Trace.of(false, ""))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testToString() {
        Trace trace = Trace.of(true, "traceId");
        OpenSpan span = trace.startSpan("operation", SpanType.LOCAL);
        assertThat(trace.toString())
                .isEqualTo("Trace{stack=[" + span + "], isObservable=true, traceId='traceId'}")
                .contains(span.getOperation())
                .contains(span.getSpanId());
    }
}

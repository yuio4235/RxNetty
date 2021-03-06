/*
 * Copyright 2015 Netflix, Inc.
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
package io.reactivex.netty.examples.http.loadbalancing;

import io.reactivex.netty.examples.ExamplesTestUtil;
import org.junit.Test;

import java.util.Queue;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class LoadBalancingTest {

    @Test(timeout = 60000)
    public void testLoadBalancing() throws Exception {
        Queue<String> output = ExamplesTestUtil.runClientInMockedEnvironment(HttpLoadBalancingClient.class);

        assertThat("Unexpected number of messages echoed", output, hasSize(5));

        assertThat("Unexpected status.", output.poll(), anyOf(containsString("HTTP/1.1 200 OK"),
                                                              containsString("HTTP/1.1 503 Service Unavailable")));
        assertThat("Unexpected status.", output.poll(), anyOf(containsString("HTTP/1.1 200 OK"),
                                                              containsString("HTTP/1.1 503 Service Unavailable")));
        assertThat("Unexpected status.", output.poll(), anyOf(containsString("HTTP/1.1 200 OK"),
                                                              containsString("HTTP/1.1 503 Service Unavailable")));
        assertThat("Unexpected status.", output.poll(), anyOf(containsString("HTTP/1.1 200 OK"),
                                                              containsString("HTTP/1.1 503 Service Unavailable")));
        assertThat("Unexpected status.", output.poll(), anyOf(containsString("HTTP/1.1 200 OK"),
                                                              containsString("HTTP/1.1 503 Service Unavailable")));

    }
}

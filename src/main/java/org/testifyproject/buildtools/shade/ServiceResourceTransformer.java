/*
 * Copyright 2016-2017 Sharmarke Aden.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testifyproject.buildtools.shade;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import org.apache.maven.plugins.shade.relocation.Relocator;
import org.apache.maven.plugins.shade.resource.ResourceTransformer;
import org.codehaus.plexus.util.IOUtil;

/**
 * A transformer that looks for service descriptors under META-INF/services and
 * relocates the service files as well as the content of the service file.
 *
 * @see
 * <a href="https://issues.apache.org/jira/browse/MSHADE-182">MSHADE-182</a>
 * @author saden
 */
public class ServiceResourceTransformer implements ResourceTransformer {

    private static final String SERVICES_PATH = "META-INF/services";
    private final Map<String, ServiceStream> serviceEntries = new HashMap<>();
    private List<Relocator> relocators;

    @Override
    public boolean canTransformResource(String resource) {
        return resource.startsWith(SERVICES_PATH);
    }

    @Override
    public void processResource(String resource, InputStream is, List<Relocator> relocators)
            throws IOException {
        ServiceStream out = serviceEntries.get(resource);
        String key = resource.substring(SERVICES_PATH.length() + 1);
        boolean relocatable = false;

        for (Relocator relocator : relocators) {
            if (relocator.canRelocateClass(key)) {
                relocatable = true;
                break;
            }
        }

        if (relocatable && out == null) {
            out = new ServiceStream();
            serviceEntries.put(resource, out);
        }

        if (out != null) {
            out.append(is);
            is.close();
        }

        if (this.relocators == null) {
            this.relocators = relocators;
        }
    }

    @Override
    public boolean hasTransformedResource() {
        return serviceEntries.size() > 0;
    }

    @Override
    public void modifyOutputStream(JarOutputStream jos)
            throws IOException {
        for (Map.Entry<String, ServiceStream> entry : serviceEntries.entrySet()) {
            String key = entry.getKey();
            ServiceStream data = entry.getValue();

            if (relocators != null) {
                key = key.substring(SERVICES_PATH.length() + 1);
                for (Relocator relocator : relocators) {
                    if (relocator.canRelocateClass(key)) {
                        key = relocator.relocateClass(key);
                        break;
                    }
                }
                key = SERVICES_PATH + '/' + key;
            }

            jos.putNextEntry(new JarEntry(key));

            PrintWriter writer = new PrintWriter(jos);
            InputStreamReader streamReader = new InputStreamReader(data.toInputStream());
            try (BufferedReader reader = new BufferedReader(streamReader)) {
                String className;

                while ((className = reader.readLine()) != null) {
                    if (relocators != null) {
                        for (Relocator relocator : relocators) {
                            if (relocator.canRelocateClass(className)) {
                                className = relocator.applyToSourceContent(className);
                                break;
                            }
                        }
                    }

                    writer.println(className);
                    writer.flush();
                }
            }

            data.reset();
        }
    }

    static class ServiceStream
            extends ByteArrayOutputStream {

        ServiceStream() {
            super(1_024);
        }

        public void append(InputStream is)
                throws IOException {
            if (count > 0 && buf[count - 1] != '\n' && buf[count - 1] != '\r') {
                write('\n');
            }

            IOUtil.copy(is, this);
        }

        public InputStream toInputStream() {
            return new ByteArrayInputStream(buf, 0, count);
        }

    }

}

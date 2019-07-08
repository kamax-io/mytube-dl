/*
 * mytube-dl - WebUI for youtube-dl
 * Copyright (C) 2019 Max Dor
 *
 * https://max.dorius.io/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.dorius.max.mytubedl.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FileUtil {

    public static String load(String loc) throws IOException {
        URI uri = URI.create(loc);

        InputStream is;
        if (StringUtils.equals("classpath", uri.getScheme())) {
            String resource = uri.getSchemeSpecificPart();
            is = FileUtil.class.getResourceAsStream(resource);
            if (Objects.isNull(is)) {
                throw new FileNotFoundException("No classpath resource: " + resource);
            }
        } else {
            is = new FileInputStream(loc);
        }

        try {
            return IOUtils.toString(is, StandardCharsets.UTF_8);
        } finally {
            is.close();
        }
    }

}

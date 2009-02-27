/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yaml.snakeyaml.types;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @see http://yaml.org/type/binary.html
 */
public class BinaryTagTest extends AbstractTest {
    String line1 = "R0lGODlhDAAMAIQAAP//9/X17unp5WZmZgAAAOfn515eXvPz7Y6OjuDg4J+fn5";
    String line2 = "OTk6enp56enmlpaWNjY6Ojo4SEhP/++f/++f/++f/++f/++f/++f/++f/++f/+";
    String line3 = "+f/++f/++f/++f/++f/++SH+Dk1hZGUgd2l0aCBHSU1QACwAAAAADAAMAAAFLC";
    String line4 = "AgjoEwnuNAFOhpEMTRiggcz4BNJHrv/zCFcLiwMWYNG84BwwEeECcgggoBADs=";
    String content = line1 + line2 + line3 + line4;

    public void testBinary() throws IOException {
        String binary = (String) getMapValue("canonical: !!binary " + content, "canonical");
        assertTrue(binary.startsWith("GIF89"));
    }

    public void testBinary2() throws IOException {
        String binary = (String) load("!!binary \"MQ==\"");
        assertEquals("1", binary);
    }

    public void testBinaryTag() throws IOException {
        String binary = (String) getMapValue("canonical: !<tag:yaml.org,2002:binary> " + content,
                "canonical");
        assertTrue(binary.startsWith("GIF89"));
    }

    public void testBinaryOut() throws IOException {
        byte[] data = "GIF89\tbi\u0003\u0000nary\n\u001Fimage\n".getBytes("ISO-8859-1");
        Map<String, String> map = new HashMap<String, String>();
        String value = new String(data, "ISO-8859-1");
        map.put("canonical", value);
        String output = dump(map);
        assertEquals("canonical: !!binary |-\n  R0lGODkJYmkDAG5hcnkKH2ltYWdlCg==\n", output);
    }

    public void testByteArray() throws IOException {
        byte[] data = { 8, 14, 15, 10, 126, 32, 65, 65, 65 };
        String value = new String(data);
        String output = dump(value);
        assertEquals("!!binary |-\n  CA4PCn4gQUFB\n", output);
        String parsed = (String) load(output);
        assertEquals(data.length, parsed.getBytes().length);
        for (int i = 0; i < data.length; i++) {
            assertEquals(data[i], parsed.getBytes()[i]);
        }
    }
}

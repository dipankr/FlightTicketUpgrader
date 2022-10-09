package org.sahaj.util;

import org.junit.jupiter.api.Test;
import org.sahaj.data.enums.CabinType;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketUtilTest {
    @Test
    void testEnumName() {
        assertTrue("ECONOMY".equals(CabinType.ECONOMY.name()));
        System.out.println(CabinType.valueOf("first".toUpperCase()));
    }

    @Test
    void testGetDate() throws ParseException {
        System.out.println(TicketUtil.getDateObject("2019-05-23"));
    }

}
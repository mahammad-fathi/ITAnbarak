
package functions.util;

import java.time.LocalDate;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.convert.FacesConverter;

/**
 *
 * @author mahamad
 */
@FacesConverter("ChangeDate")
public class ConverterDate implements javax.faces.convert.Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        Date date1 = (Date) value;
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime()); // convert to sql date
        LocalDate toLocalDate = sqlDate.toLocalDate(); //change for get year & month & day
        int[] faTArigh = gregorian_to_jalali(toLocalDate.getYear(), toLocalDate.getMonthValue(), toLocalDate.getDayOfMonth()); //change to farsi date
        return PersianDigit(Integer.toString(faTArigh[2])) + "-" + PersianDigit(Integer.toString(faTArigh[1])) + "-" + PersianDigit(Integer.toString(faTArigh[0])); // change to farsi string for show
    }

    public int[] gregorian_to_jalali(int gy, int gm, int gd) {
        int[] g_d_m = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int jy;
        if (gy > 1600) {
            jy = 979;
            gy -= 1600;
        } else {
            jy = 0;
            gy -= 621;
        }
        int gy2 = (gm > 2) ? (gy + 1) : gy;
        int days = (365 * gy) + ((int) ((gy2 + 3) / 4)) - ((int) ((gy2 + 99) / 100)) + ((int) ((gy2 + 399) / 400)) - 80 + gd + g_d_m[gm - 1];
        jy += 33 * ((int) (days / 12053));
        days %= 12053;
        jy += 4 * ((int) (days / 1461));
        days %= 1461;
        if (days > 365) {
            jy += (int) ((days - 1) / 365);
            days = (days - 1) % 365;
        }
        int jm = (days < 186) ? 1 + (int) (days / 31) : 7 + (int) ((days - 186) / 30);
        int jd = 1 + ((days < 186) ? (days % 31) : ((days - 186) % 30));
        int[] out = {jy, jm, jd};
        return out;
    }

    public String PersianDigit(String s) {

        StringBuilder ss = new StringBuilder();
        char[] d = new char[s.length()];

        for (int i = 0; i < d.length; i++) {
            d[i] = s.charAt(i);
            if (d[i] >= '0' && d[i] <= '9') {
                d[i] = ((char) (d[i] - 48 + 1632));
            }
            ss.append(d[i]);
        }
        return ss.toString();
    }
} 

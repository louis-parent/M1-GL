package fr.umontpellier.tp2;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SensorTypes
{
    public static List<SensorType> getAll()
    {
        List<SensorType> types = new ArrayList<SensorType>();

        Class<Sensor> c = Sensor.class;
        List<Field> fields = Arrays.asList(c.getDeclaredFields());

        for(Field idField : fields)
        {
            if(Modifier.isStatic(idField.getModifiers()) && idField.getName().startsWith("TYPE_") && !idField.getName().equals("TYPE_ALL"))
            {
                try
                {
                    Field nameField = c.getField("STRING_" + idField.getName());

                    int id = idField.getInt(null);
                    String name = (String) nameField.get(null);

                    types.add(new SensorType(id, name));
                } catch (IllegalAccessException | NoSuchFieldException e) {}
            }
        }

        return types;
    }
}

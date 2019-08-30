package cn.akira.util;

import cn.akira.pojo.User;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


@SuppressWarnings("unused")
public class CastUtil {
    public static User genderCast(User user) {
        if (user.getUserInfo().getGender() == null) {
            user.getUserInfo().setGender("0");
        }
        switch (user.getUserInfo().getGender()) {
            case "1":
                user.getUserInfo().setGender("男");
                break;
            case "2":
                user.getUserInfo().setGender("女");
                break;
            case "3":
                user.getUserInfo().setGender("其他");
                break;
            default:
                user.getUserInfo().setGender("未知");
                break;
        }
        return user;
    }

    public static Object emptyStrToNull(Object javaObj) throws Exception{
        Class<?> clazz = javaObj.getClass();
        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 可访问私有变量
            field.setAccessible(true);
            // 获取属性类型
            String type = field.getGenericType().toString();
            // 如果type是类类型，则前面包含"class "，后面跟类名
            if ("class java.lang.String".equals(type)) {
                // 将属性的首字母大写
                String methodName = field.getName().replaceFirst(field.getName().substring(0, 1),
                        field.getName().substring(0, 1).toUpperCase());
                System.out.println(methodName);
                Method methodGet = clazz.getMethod("get" + methodName);
                // 调用getter方法获取属性值
                String str = (String) methodGet.invoke(javaObj);
                if (StringUtils.isBlank(str)) {
                    System.out.println(field.getType()); // class java.lang.String
                    // 如果为null的String类型的属性则重新复制为空字符串
                    field.set(javaObj, field.getType().getConstructor(field.getType()).newInstance(""));
                }
            }
        }
        return javaObj;
    }
}

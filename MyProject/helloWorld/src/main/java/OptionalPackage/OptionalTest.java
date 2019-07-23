package OptionalPackage;

import java.util.Optional;

/**
* @Description:    该类验证java8 新特性  optional
* @Author:         YYF
* @CreateDate:     2019/4/15 11:21
* @Version:        1.0
*/
public class OptionalTest {
    public static void main(String[] args) {

        OptionalTest optionalTest = new OptionalTest();
        Integer integer = null;
        Integer integer1 = new Integer(2);
        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> opt1 = Optional.ofNullable(integer);
        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> opt2 = Optional.of(integer1);

        Integer sum = optionalTest.sum(opt1, opt2);
        System.out.println("求和："+sum);
    }


    public Integer sum(Optional<Integer> num1, Optional<Integer> num2){

        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数num1存在："+num1.isPresent());
        System.out.println("第一个参数num2存在："+num2.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = num1.orElse(new Integer(0));
        //Optional.get - 获取值，值需要存在
        Integer value2 = num2.get();

        System.out.println("value1:"+value1+" --- value2:"+value2);
        return value1+value2;
    }


}

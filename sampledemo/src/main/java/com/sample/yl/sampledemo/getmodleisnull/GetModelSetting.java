package com.sample.yl.sampledemo.getmodleisnull;

/**
 * Created by ${jz} on 2018/4/23。
 * <p>
 * 自动生成判断为null的字符串
 * 及数组为空的实体字段模板
 */
public class GetModelSetting {

//  #if($field.modifierStatic)
//    static ##
//            #end
//    $field.type ##
//            #set($name = $StringUtil.capitalizeWithJavaBeanConvention($StringUtil.sanitizeJavaIdentifier($helper.getPropertyName($field, $project))))
//            #if ($field.boolean && $field.primitive)
//            #if ($StringUtil.startsWithIgnoreCase($name, 'is'))
//            #set($name = $StringUtil.decapitalize($name))
//            #else
//    is##
//            #end
//#else
//    get##
//            #end
//    ${name}() {
//  #if ($field.string)
//            return $field.name == null ? "" : $field.name;
//  #else
//    #if ($field.list)
//            if ($field.name == null) {
//                return new ArrayList<>();
//            }
//        return $field.name;
//    #else
//        return $field.name;
//    #end
//  #end
//    }

}

//
//  YLAreaPickerView.h
//  YLAppUtility
//
//  Created by zhangyilong on 16/7/29.
//  Copyright © 2016年 zhangyilong. All rights reserved.
//

#import "JsonObject.h"

@interface ${entityName} : JsonObject


<#list fields as field>

@property(nonatomic, strong) ${field.type} ${field.name};

</#list>

@end
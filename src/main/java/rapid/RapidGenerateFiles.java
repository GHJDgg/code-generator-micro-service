package rapid;

import cn.org.rapid_framework.generator.GeneratorFacade;

import java.io.File;

public class RapidGenerateFiles {
     public static void main(String[] args) throws Exception{
         /**
          * 用法:例子如下
          *
          * 0.首先修改resources/generator.xml,修改为当前正确的信息(表注释,模块名(如:goods),作者姓名,表前缀,数据库用户名/密码)
          * 1.修改以下代码中的表名(如:goods_cate)
          * 2.右击 Run,成功后,查看output目录
          * 3.将service-goods-api,service-goods-app,service-goods-bean,service-goods-core4个目录直接拷贝到sbc-service-goods模块
          * 4.根据情况,删除多生成的代码
          */
         GeneratorFacade g = new GeneratorFacade();
         g.getGenerator().addTemplateRootDir(System.getProperty("user.dir").concat(File.separator).concat("template_v3"));
         // 参数为表名
         g.generateByTable ("cate_data");
     }
}

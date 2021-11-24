# xxl-job-executor-站长工具

#### 介绍

**本项目是服务于我个人的博客，用于加快百度收录和百度数据统计。由于很多地方需要定时查询，所以该项目也依赖于[xxl-job](https://gitee.com/xuxueli0323/xxl-job)。**

#### 主要功能

- 友情链接管理。
- 百度统计数据整合。
- 自动获取百度统计access_token。
- 自动推送博文到百度收录。

#### 软件架构

- 主要技术栈：`nacos、springboot、mybatis、dom4j`等技术,`nacos`是个人用来练手的，不喜欢可以将配置文件都写在`application.yml`中。

#### 安装教程

1. 导入数据库脚本

   ```
   脚本位置：xxl-job-executor\doc\数据库脚本\executor
   在mysql中直接执行即可
   ```

2. 导入`nacos`数据

   ```
   如果不是学习nacos，本项目不推荐使用nacos。先删除application.yml和bootstrap.yml，然后将application-noNacos.yml重命名为application.yml即可。
   ```

   ![image.png](https://ae04.alicdn.com/kf/He49ab63ed3ce455fb53a8db070da7a46v.png)

#### 使用说明

- 项目主要功能图解
  ![image-20210705141446133](https://images.chenmx.net//blog20210705143851.png!halo)

#### 关于echarts统计图表部分

- 由于鄙人是个前端大菜鸡，所以让我在前端拼echart的数据是根本不可能的事情啦，通过观察，发现`echarts`图其实有很多相通的属性，随意俺就把它每个属性都封装成一个实体类了，数据传到前端仅仅需要短短的几句就可以把漂亮的`echarts`图渲染出来，如：

  ```
  var chartDom = document.getElementById('main');
  var myChart = echarts.init(chartDom);
  var option;
  option && myChart.setOption(option);
  ```

- 我在这里是后端把全部需要的数据做了处理，这段仅针对使用`halo`主题的朋友，如果有大佬其实可以做成可以配置的，将`access_token`配置在主题的配置文件,后端提供需要数据的统一接口，这样就可以造福广大`halo`博友了。无奈鄙人前端太菜，完成不了，哭唧唧`ing`。

- 有关ACCESS_TOKEN获取的教程，请参考我的[另一篇文章](https://www.chenmx.net/?p=242)：https://www.chenmx.net/?p=242
  按照以上的文档应该可以正常启动项目了，如果启动不了，可以在我的[小破站](https://www.chenmx.net):https://www.chenmx.net留言反馈，我看到都会一一回答，当然我也是只菜鸡，很多地方赶时间都没设计好，欢迎各位看官们积极指出。


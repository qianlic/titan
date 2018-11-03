/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.12 : Database - titan
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`titan` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `clr_crawler` */

DROP TABLE IF EXISTS `clr_crawler`;

CREATE TABLE `clr_crawler` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `resumable` bit(1) DEFAULT NULL,
  `seed` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `clr_crawler` */

/*Table structure for table `clr_page` */

DROP TABLE IF EXISTS `clr_page`;

CREATE TABLE `clr_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` tinyblob,
  `contentCharset` varchar(255) DEFAULT NULL,
  `contentEncoding` varchar(255) DEFAULT NULL,
  `contentType` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `redirect` bit(1) NOT NULL,
  `redirectedToUrl` varchar(255) DEFAULT NULL,
  `statusCode` int(11) NOT NULL,
  `urlId` bigint(20) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `clr_page` */

/*Table structure for table `clr_weburl` */

DROP TABLE IF EXISTS `clr_weburl`;

CREATE TABLE `clr_weburl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `anchor` varchar(255) DEFAULT NULL,
  `depth` smallint(6) NOT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `parentUrl` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `subDomain` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `clr_weburl` */

/*Table structure for table `com_article` */

DROP TABLE IF EXISTS `com_article`;

CREATE TABLE `com_article` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `content` text,
  `author` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `com_article` */

insert  into `com_article`(`id`,`title`,`tags`,`content`,`author`,`status`,`ts`) values (4,'哲学主要派别','测试一波:#00B5FF','<ol><li><span style=\"color: rgb(0, 71, 178);\">唯物主义和唯心主义（基本派别） ——世界本源问题，是什么？</span></li><li><span style=\"color: rgb(0, 71, 178);\">辩证法（联系的发展的）与形而上学（禁止的孤立的） ——世界存在状态问题，怎么样？</span></li></ol><p class=\"ql-indent-1\"><span style=\"color: rgb(240, 102, 102);\">注意：辩证法和形而上学是两种对立的发展学说，不构成哲学的基本派别，他们从属与唯物主义和唯心主义</span></p><p><br></p><p><strong style=\"color: rgb(0, 102, 204);\" class=\"ql-size-large\">唯物主义</strong></p><p><strong style=\"color: rgb(102, 163, 224);\">主张：存在（物质）第一性，思维（意识）第二性，存在决定思维、物质决定意识。</strong></p><p><strong style=\"color: rgb(102, 163, 224);\">形态：古代朴素唯物主义；近代形而上学唯物主义；辩证唯物主义和历史唯物主义。</strong></p><p><br></p><p><strong>古代朴素唯物主义</strong></p><p>基本观点：把物质归结为具体的有形物体，认为金、木、水、火、气等是世界的本源。</p><p>进步性：否认世界是神创造的，认为世界是物质的，对唯物主义后来的发展有重大影响</p><p>局限性：把抽象的物质归结为具体的物质形态，复杂问题简单化，有其历史的局限性。</p><p><strong>近代形而上学唯物主义</strong></p><p>基本观点：又称机械唯物主义。把物质归结为自然科学意义上的原子，认为原子是世界的本原，原子的属性就是物质的属性，因而具有机械性。</p><p>进步性：丰富和发展了唯物主义。</p><p>局限性：把具体形态的原子等同于物质，具有机械性。</p><p><strong>辩证唯物主义和历史唯物主义</strong></p><p>基本观点：辩证唯物主义认为物质决定意识，意识是物质的反映具有能动作用。历史唯物主义认为社会存在决定社会意识，社会意识对社会存在有能动作用。</p><p>进步性：正确揭示了物质世界的基本规律，反映了社会历史发展的客观要求。</p><p><br></p><p><strong style=\"color: rgb(0, 102, 204);\" class=\"ql-size-large\">唯心主义</strong></p><p><strong style=\"color: rgb(102, 163, 224);\">主张：思维（意识）第一性，存在（物质）第二性，思维决定存在、意识决定物质。</strong></p><p><strong style=\"color: rgb(102, 163, 224);\">形态：主观唯心主义；客观唯心主义。</strong></p><p><br></p><p><strong>主观唯心主义</strong></p><p>基本观点：把人的主观精神作为世界的本原，认为世界是人的主观意志的产物或表现。</p><p><strong>客观唯心主义</strong></p><p>基本观点：不是指人的主观精神，而是指脱离人而单独存在的所谓“客观”精神（上帝理念，绝对精神等）是世界的本原，认为现实的物质世界只是客观精神的外化和表现。</p><p><br></p><p><br></p><p><strong class=\"ql-size-large\" style=\"color: rgb(0, 71, 178);\">辩证法</strong></p><p><strong>古代朴素辩证法</strong>：万事万物是相互联系、相互作用不断变化和发展的。</p><p><strong>唯心主义辩证法</strong>：黑格尔为代表。世界是有机的统一体，是不断运动、变化和发展的。</p><p><strong>马克思主义唯物辩证法</strong>。</p><p><br></p><p>哲学发展的趋向是唯物论和辩证法的统一</p><p>马克思主义哲学是科学世界观和方法论的统一。</p><p>马哲产生的历史根源（必然性）</p><p>时代背景：19世纪40年代，资本主义的发展、社会矛盾激化，无产阶级力量壮大。</p><p>自然科学前提：19世纪上半叶自然科学领域有了重大发展，能量守恒定律、细胞学说和生物进化论促进了马哲的产生。</p><p>理论来源：19世纪德国古典哲学成为马克思主义哲学的直接理论来源。</p><p>基本观点：实践的观点</p><p>意义：创立了唯物史观，结束了社会历史领域中唯心史观的统治地位。结束了旧唯物主义缺乏能动原则的状况。为无产阶级解放提供了思想武器。</p><p>精髓：解放思想、实事求是、与时俱进。</p><p>基本特征：阶级性、实践性与革命性和科学性的高度统一。</p><p>科学性是革命性的基础，革命性是科学性的必然结果。科学性和革命性建立在阶级性和实践性的基础上</p><p>阶级性：马哲是无产阶级的思想体系，反映了无产阶级的需要和需求。</p><p>实践性：马哲强调理论对实践的依赖关系，理论的基础是实践。理论和实践的统一是马哲的根本原则。</p><p>革命性：马哲强调世界是物质的，物质是运动的，从事物的暂时性方面去理解，不崇拜任何东西。</p><p>科学性：对自然、社会、思维发展的普遍规律的科学总结，没有片面性，并随实践的发展而发展。</p>','admin','','2018-09-27 22:35:35'),(6,'红包谢谢',NULL,'<p><img src=\"https://i.loli.net/2018/09/28/5bad8e9899325.png\" width=\"600\" height=\"900\"></p>','admin','','2018-09-28 10:15:04'),(8,'物质意识','dddd:#19be6b','<p><strong>辩证唯物主义的物质观</strong></p><p>物质是标志<u style=\"color: rgb(230, 0, 0);\">客观存在</u>的哲学范畴</p><p>运动是物质的根本属性和存在方式</p><p>物质运动的客观规律性</p><p><br></p><p><strong>意识的起源、本质和作用</strong></p><p>物质世界（自然界、社会）长期发展的产物</p><p>人脑的机能，人脑对客观世界的主观映象</p><p>能动地认识世界，能动地改造世界</p><p><br></p><p><strong>世界的物质统一性</strong></p><p>世界是多样的又是统一的，即整个世界有其共同的基础和本质。</p><p>世界的统一性在与物质性。</p><p>物质世界的统一是多样性的统一。</p><p>自然界和人类社会都具有物质统一性。</p>','admin','','2018-09-28 23:38:01'),(9,'唯物辩证法','测试:#19be6b,新鲜:#00BCD4','<p>2大特征：普遍联系和永恒发展</p><p>3大规律：对立统一、质量互变、否定之否定</p><p>5大范畴：现象与本质；形式与内容；原因与结果；必然性与偶然性；可能性与现实性</p><p><br></p><p>事物普遍联系</p><p>定义：一切事物之间、事物内部诸要素之间的相互作用、相互制约和相互影响</p><p>特点：客观性、普遍性、多样性</p><p>事物永恒发展</p><p>实质：新事物的产生，旧事物的灭亡</p><p><br></p><p>对立统一规律</p><p>矛盾及其基本特征：矛盾是指事物之间以及事物内部诸要素之间既对立又统一的关系。</p><p>矛盾的基本属性：同一性和斗争性</p><p>矛盾的作用：事物发展的动力</p><p>矛盾具有普遍性和特殊性：普遍性指矛盾无处不在，特殊性指事物各自有特点，不同事物或同一事物在不同阶段有不同矛盾</p><p>质量互变规律</p><p>事物是质和量的统一</p><p>质变和量变是相互转化、辩证统一的关系</p><p>量变是度范围内，质变是突破度的范围，量变是质变的准备阶段，质变是量变的必然结果，质变引起新量变</p><p>否定之否定规律</p><p>否定是客观的：否定是促使事物自身灭亡的方面，肯定是事物维持自身存在的方面</p><p>否定是辩证的：否定是事物内在矛盾引起的自我否定，否定是包含肯定的否定，是扬弃</p><p>否定观的过程：肯定-》否定-》否定的否定（新的肯定）</p><p><br></p><p>现象和本质</p><p>本质：事物的根本性质和事物基本要素的内在联系</p><p>现象：事物的表面特征和外部联系。有真象和假象之分</p><p>关系：事物的现象和本质是辩证统一的</p><p>形式和内容</p><p>内容：构成事物的一切要素的总和，即事物的各种内在矛盾</p><p>形式：表现事物要素总和的方式</p><p>关系：相互区别又相互作用</p><p>原因和结果</p><p>原因：引起某种现象的现象</p><p>结果：被某种现象引起的现象</p><p>关系：引起和被引起的关系</p><p>必然性和偶然性</p><p>必然性：事物联系发展过程中合乎规律的、确定不移的趋势</p><p>偶然性：事物联系发展过程中的不确定的趋势</p><p>关系：相互对立，相互统一</p><p>可能性和现实性</p><p>现实性：在事物的内在发展过程中，符合规律的一切事物和现象的实际存在性</p><p>可能性：包含在事物中的、预示着未来发展前途的种种趋势，是潜在的，尚未实现的东西</p>','admin','','2018-09-29 22:27:12'),(10,'2017成考英语','对对对:#5D4037','<p><strong style=\"color: black;\">I.Phonetics ( 5 points)</strong></p><p><span style=\"color: black;\">Directions:In each of the following groups of words, there are four underlined letters or letter combinations marked A, B, C and D. Compare the underlined parts and iden-tify the one that is different from the others in pronunciation. Mark your answerby blackening the corresponding letter on the Answer Sheet.</span></p><p><span style=\"color: black;\">1. A. p</span><u style=\"color: black;\">e</u><span style=\"color: black;\">nalty&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. mom</span><u style=\"color: black;\">e</u><span style=\"color: black;\">nt&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. quarr</span><u style=\"color: black;\">e</u><span style=\"color: black;\">l&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. abs</span><u style=\"color: black;\">e</u><span style=\"color: black;\">nt</span></p><p><span style=\"color: black;\">2. A. symp</span><u style=\"color: black;\">a</u><span style=\"color: black;\">thy&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. materi</span><u style=\"color: black;\">a</u><span style=\"color: black;\">l&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. cour</span><u style=\"color: black;\">a</u><span style=\"color: black;\">ge&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. an</span><u style=\"color: black;\">a</u><span style=\"color: black;\">lysis</span></p><p><span style=\"color: black;\">3. A. starva</span><u style=\"color: black;\">tion</u><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. sugges</span><u style=\"color: black;\">tion</u><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. satisfac</span><u style=\"color: black;\">tion</u><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. situa</span><u style=\"color: black;\">tion</u></p><p><span style=\"color: black;\">4. A. donk</span><u style=\"color: black;\">ey</u><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. turk</span><u style=\"color: black;\">ey</u><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. mon</span><u style=\"color: black;\">ey</u><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. ob</span><u style=\"color: black;\">ey</u></p><p><span style=\"color: black;\">5. A. revi</span><u style=\"color: black;\">s</u><span style=\"color: black;\">e&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. consi</span><u style=\"color: black;\">s</u><span style=\"color: black;\">t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. adverti</span><u style=\"color: black;\">s</u><span style=\"color: black;\">e&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. vi</span><u style=\"color: black;\">s</u><span style=\"color: black;\">it</span></p><p><strong style=\"color: black;\">Ⅱ. Vocabulary and Structure ( 15 points )</strong></p><p><span style=\"color: black;\">&nbsp;Directions : There are 15 incomplete sentences in this section. For each sentence there are four choices marked A, B, C and D. Choose one answer that best completes the sentence and blacken the corresponding letter on the Answer Sheet.</span></p><p><span style=\"color: black;\">&nbsp;6. Jonathan and Joe left the house to go for__ after supper.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. walk&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. the walk</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. wallks&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. a walk</span></p><p><span style=\"color: black;\">&nbsp;7. He pointed at the new car and asked, \"___ is it? Have you ever seen it before?\"</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. Why&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. Where</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. Who&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. Whose</span></p><p><span style=\"color: black;\">8. My father asked __ to help with his work.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;A. I and Tom&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. Tom and me</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;C. me and Tom&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. Tom and I</span></p><p><span style=\"color: black;\">9. Nowadays little knowledge __ to be a dangerous thing.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;A. seem&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. seemed</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;C. does seem&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. do seem</span></p><p><span style=\"color: black;\">10. If their marketing team succeeds, they __ their profits by 20 percent.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. will increase&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. would be increasing</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. will have increased&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. would have been increasing</span></p><p><span style=\"color: black;\">11. You\'d better take these documents with you __ you need them for the meeting.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. unless&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. in case</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. until&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. so that</span></p><p><span style=\"color: black;\">12. I haven\' t been to a pop festival before and Mike hasn\' t __</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. too&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. as well</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. neither&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. either</span></p><p><span style=\"color: black;\">13.__ is known to the world, Mark Twain was a great American writer.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. As&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. Once</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. That&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. It</span></p><p><span style=\"color: black;\">14. John complained to the bookseller that there were several pages______ in the dictionary.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. lacking&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. losing</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. missing&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. dropping</span></p><p><span style=\"color: black;\">15. Not until the game had begun __ at the sports ground.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. should he have arrived&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. would he have arrived</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. did he arrive&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. had he arrived</span></p><p><span style=\"color: black;\">16. Moviegoers know that many special effects are created by computers,</span><u style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u><span style=\"color: black;\">they often don\' tknow is that these scenes still require a lot of work.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. That&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. Whom</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. What&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. How</span></p><p><span style=\"color: black;\">17. The president is to give a formal __ at the opening ceremony.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. speech&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. debate</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. discussion&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. argument</span></p><p><span style=\"color: black;\">18. When I am confronted with such questions, my mind goes</span><u style=\"color: black;\"> __</u><span style=\"color: black;\">, and I can hardly remember myown date of birth.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. faint&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. blank</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. dark&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. blind</span></p><p><span style=\"color: black;\">19. If they are willing to lend us the money we need,all our problems will be__</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. solved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. caused</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. covered&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. met</span></p><p><span style=\"color: black;\">20. This article __ more attention to the problem of cultural conflicts.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. cares for&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. allows for</span></p><p>	<span style=\"color: black;\">C. applies for&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. calls for</span></p><p><strong style=\"color: black;\">Ⅲ. Cloze ( 30 points)</strong></p><p><span style=\"color: black;\">Directions:For each blank in the following passage, there are four choices marked A, B0 Cand D. Choose the one that is most suitable and mark your answer by blackeningthe corresponding letter on the Answer Sheet.</span></p><p>	<span style=\"color: black;\">What enables some people to get big creative breakthroughs while others only get small and non-creative breakdowns, blaming themselves and society? Are some people \"gifted\"? Are there other factors </span><u style=\"color: black;\">&nbsp;&nbsp;21&nbsp;&nbsp;</u><span style=\"color: black;\">work--factors that we have more control over than we think?</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;While nobody can deny the </span><u style=\"color: black;\">&nbsp;&nbsp;22&nbsp;&nbsp;</u><span style=\"color: black;\">that some people seem to be blessed with particular creativity, research shows that anyone can </span><u style=\"color: black;\">&nbsp;&nbsp;23&nbsp;&nbsp;</u><span style=\"color: black;\">their chances of coming up with new and original ideas</span><u style=\"color: black;\">&nbsp;&nbsp;24&nbsp;&nbsp;</u><span style=\"color: black;\">they would only engage themselves more in the process of&nbsp;&nbsp;25&nbsp;. It\' s the old Thomas Edison thing about \"discovery</span><u style=\"color: black;\">&nbsp;26</u><span style=\"color: black;\">&nbsp;&nbsp;99 percent perspiration (汗水) and 1 percent inspiration. \"</span></p><p><u style=\"color: black;\">&nbsp;&nbsp;27&nbsp;&nbsp;</u><span style=\"color: black;\">, the studies prove this:great creative breakthroughs usually happen only&nbsp;&nbsp;</span><u style=\"color: black;\">28&nbsp;&nbsp;</u><span style=\"color: black;\">intense periods of struggle. It is sustained effort towards a specific goal&nbsp;&nbsp;</span><u style=\"color: black;\">29&nbsp;&nbsp;</u><span style=\"color: black;\">eventually prepares for great creative insights.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;This kind of sustained effort does not always</span><u style=\"color: black;\">&nbsp;&nbsp;30&nbsp;&nbsp;</u><span style=\"color: black;\">immediate results, a fact that not only separates the innovators (革新者) from non-innovators, but</span><u style=\"color: black;\">&nbsp;31&nbsp;</u><span style=\"color: black;\">leads some people to conclude that it is just not&nbsp;&nbsp;32&nbsp;&nbsp;for them. \"Maybe I should have gone to medical school like my mother wanted,\" they wonder when the breakthrough is </span><u style=\"color: black;\">&nbsp;&nbsp;33&nbsp;&nbsp;</u><span style=\"color: black;\">to be found. Alas, one forgets during inevitable encounters</span><u style=\"color: black;\">&nbsp;&nbsp;34</u><span style=\"color: black;\">&nbsp;&nbsp;self-doubt,that the big surprise is never</span><u style=\"color: black;\">&nbsp;35&nbsp;</u><span style=\"color: black;\">. Indeed,it can&nbsp;happen at any time and place.</span></p><p><span style=\"color: black;\">21. A. to&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. in&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. at&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D.by</span></p><p><span style=\"color: black;\">22. A. issue&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. problem&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. reason&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. fact</span></p><p><span style=\"color: black;\">23. A. miss&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. reduce&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. increase&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D.lose</span></p><p><span style=\"color: black;\">24. A. because&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. if&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. while&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. whether</span></p><p><span style=\"color: black;\">25. A. creation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. practice&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. production&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. achievement</span></p><p><span style=\"color: black;\">26. A. being&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. be&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. was&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. were</span></p><p><span style=\"color: black;\">27. A. Sooner or later&nbsp;</span></p><p><span style=\"color: black;\">B. Some day or other&nbsp;&nbsp;</span></p><p><span style=\"color: black;\">C. Every now and then</span></p><p><span style=\"color: black;\">&nbsp;D. Time and again</span></p><p><span style=\"color: black;\">28. A. beyond&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. after&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. above&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. through</span></p><p><span style=\"color: black;\">29. A. that&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. who&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. what&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. as</span></p><p><span style=\"color: black;\">30. A. create&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. produce&nbsp;&nbsp;&nbsp;&nbsp;C. inspire&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. encourage</span></p><p><span style=\"color: black;\">31. A. too&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. once&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. again&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. also</span></p><p><span style=\"color: black;\">32. A. good&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. difficult&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. possible&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. stupid</span></p><p><span style=\"color: black;\">33. A. anywhere&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. everywhere&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. somewhere&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. nowhere</span></p><p><span style=\"color: black;\">34. A. against&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. across&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. with&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. into</span></p><p><span style=\"color: black;\">35. A. far away&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. used up&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C. cleared off&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. near by</span></p><p><strong style=\"color: black;\">IV. Reading Comprehension ( 60 points)</strong></p><p><span style=\"color: black;\">Directions:There are five reading passages in this part. Each passage is followed by four questions. For each question there are four suggested answers marked A, B, C and D.Choose the best answer and blacken the corresponding letter on the Answer Sheet.</span></p><p><span style=\"color: black;\">&nbsp;Passage One</span></p><p><span style=\"color: black;\">&nbsp;Debate is a valuable way to practise communicating. It can also bring long-lasting rewards,especially for people working with Western businesses. The main activity of debate is presenting one\' s opinion and suppmting it with evidence,such as statistics or facts. It is a way of persuasive communication.</span></p><p><span style=\"color: black;\">Charles Lebeau helped create the \"Discover Debate\" method. He says debate is important to understanding how people communicate in Western business. Successful debaters learn how to give their opinkm,reasans and support. \"What we are trying to do is to develop a kind of thinking or approach to discussion and how to interact (交流) with someone else\' s opinion, rather than brush their opinion aside. \"</span></p><p><span style=\"color: black;\">&nbsp;Debate skills are also important in selling a product, he says. In that situation, the judges are the customem. \"So on Monday, for example, one company may come in and present their </span><u style=\"color: black;\">case</u><span style=\"color: black;\"> to the customer and they\" ll make as strong a ease as they can. On Tuesday, the next day, another company will come in and present their ease to the customer. Usually the party that can present the strongest case wins”</span></p><p><span style=\"color: black;\">&nbsp;Debate also strengthens critical thinking. In other words, it helps students learn to ask questionsand try to understand someone\' s reasons and evidence.lift-. Lebeau points out that successful debaters learn to listen carefully to what other people are saying. Then, they look for the weak points in someone else\' s opinion or argument. He says debate teaches a systematic way of questioning.</span></p><p><span style=\"color: black;\">Successful debaters also learn to think from someone else\' s point of view. Mr. Lebeau says debate can help broaden the mind. \"There\' s an expression in English : don\' t criticize another person before you have walked in their shoes. I think the wonderful thing about debate is, it puts us in another person\' s shoes. \"</span></p><p><span style=\"color: black;\">36. According to Paragraph 1 ,what is the purpose of debate?</span></p><p><span style=\"color: black;\">A. To bring long-lasting material rewards.</span></p><p><span style=\"color: black;\">B. To present evidence such as statistics and facts.</span></p><p><span style=\"color: black;\">C. To respond to questions in a systematic way.</span></p><p><span style=\"color: black;\">D. To persuade people to accept your opinions.</span></p><p><span style=\"color: black;\">37. Why is debate important.9</span></p><p><span style=\"color: black;\">A. It helps people understand others better.</span></p><p><span style=\"color: black;\">B. It allows people to present their opinions.</span></p><p><span style=\"color: black;\">C. It develops one\' s thinking and communicative competence.</span></p><p><span style=\"color: black;\">D. It gives one the opportunity to brush others\' opinion aside.</span></p><p><span style=\"color: black;\">&nbsp;38. What does the underlined word \"case\" in Paragraph 3 refer to?</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;A. Container.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. Evidence.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;C. Problem.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. Product.</span></p><p><span style=\"color: black;\">&nbsp;39. What can debaters benefit from \"walking in another person\' s shoes\" .9</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;A. Becoming more broad-minded.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. Developing critical thinking.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;C. Finding others\' weak points.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. Trying out others\' methods.</span></p><p class=\"ql-align-center\"><strong style=\"color: black;\">Passage Two</strong></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;We all love a hero, and rescue dogs are some of the biggest heroes of all. You will often find them going above and beyond duty to save someone, risking--and at times losing--their lives in the process.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;Rescue dogs are generally found in the Sporting and Hunting Groups, or from the traditional Herding Group. These types include the Bloodhound, Labrador Retriever, Newfoundland, German Shepherd, Golden Retriever, and Belgian Malinois--all of which are chosen for search-and-rescue duty because of their amazing physical strength, loyalty, and their tendency for mental stability.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;These types also have a keen sense of hearing and smell--to better locate lost individuals—and are often able to access hard-to-reach areas. As highly trained animals, they serve in many different fields, including specialist search, snow slide rescue, dead body location, and tracking.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;To overcome obstacles and succeed when performing the demanding duties of a search-and-rescue worker, a dog must display certain qualities. In addition to intelligence and strength, the dog must be swift, confident, easily trainable, adaptable, and have a high level of stamina (耐力) and endurance.</span></p><p><span style=\"color: black;\">A strong sense of group cooperation and an ability to engage in friendly play during \"down\" time is also required of search-and-rescue dogs.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A rescue dog goes through many, many hours of intensive training to be fit for duty. Training is not for the faint-hearted. Certification training can take from two to three years, working three to four hours a day, three to six days a week, often in group,team-oriented sessions.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Each search-and-rescue field requires different types of training. Rescue training, for instance, includes \"air scenting\"--where dogs are trained to smell the air for the victim\' s scent (气味) and then follow the scent to the person. This ability is crucial to finding victims trapped under collapsed buildings and snow slide.</span></p><p><span style=\"color: black;\">40. Rescue dogs are chosen probably because__</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. they are loyal&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. they are brave</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. they have amazing appearances&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. they have good eyesight</span></p><p><span style=\"color: black;\">41. What does \"faint-hearted\" in Paragraph 5 mean??</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. Courageous.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. Cowardly.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. Energetic.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. Slow.</span></p><p><span style=\"color: black;\">42. Which ability is most important for dogs to rescue people trapped in snow?</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. Sharp hearing.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. Swift movement.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. Extraordinary smelling.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. A strong memory.</span></p><p><span style=\"color: black;\">43. What is the passage mainly about?</span></p><p>	<span style=\"color: black;\">A. Selection process of rescue dogs.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p><p>	<span style=\"color: black;\">B. Qualities and training of rescue dogs.</span></p><p>	<span style=\"color: black;\">C. Risks rescue dogs are faced with.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p><p>	<span style=\"color: black;\">D. Types of tasks rescue dogs can perform.</span></p><p class=\"ql-align-center\"><strong style=\"color: black;\">Passage Three</strong></p><p>	<span style=\"color: black;\">&nbsp;Eating an apple a day doesn\' t keep the doctor away, but it does reduce the amount of trips you make to the drug store per year. That \' s according to a new study that investigates whether there\' s any truth in the old saying.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;A team of researchers led by Dr Matthew Davis, of the University of Michigan School of Nursing,asked 8,399 participants to answer survey questions about diet and health. A total of 753 were apple eaters, consuming at least 149g of raw apple per day. The remaining 7,646 were classed as non-apple eaters. When both groups answered questions on trips to the doctor and trips to the drug store per year,the apple eaters were found to be 27% less likely to visit the druggist for drugs.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;Trips to the doctor were not significantly affected by apple consumption, though. \"Evidence does not support that an apple a day keeps the doctor away. However, the small number of US adults who eat an apple a day does appear to use fewer prescription medications,\" the study concludes.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;Apple eaters were also found to be less likely to smoke and be more likely to have a higher educational attainment than non-apple eaters. While apples do not compete with oranges, they docontain some immune (免疫的) system-increasing vitamin C, which may be why apple-eaters visit the druggist less. With over 8mg of vitamin C per medium-sized fruit, an apple can provide roughly 14% your daily recommended intake.</span></p><p><span style=\"color: black;\">&nbsp;Previous studies have also linked apple consumption to a lower risk of Type 2 diabetes (二型糖尿病) ,improved lung function and a lower risk of colon (结肠) cancer.</span></p><p><span style=\"color: black;\">44. How many non-apple eaters answered survey questions in the research?</span></p><p><span style=\"color: black;\">A. 149.&nbsp;</span></p><p><span style=\"color: black;\">B. 7,646.</span></p><p><span style=\"color: black;\">C. 753.</span></p><p><span style=\"color: black;\">D. 8,399.</span></p><p><span style=\"color: black;\">45. What is the conclusion of the study?</span></p><p><span style=\"color: black;\">&nbsp;A. Apple consumption has greatly reduced US adults\' trips to the doctor.</span></p><p><span style=\"color: black;\">&nbsp;B. An apple a day does keep the doctor away.</span></p><p><span style=\"color: black;\">&nbsp;C. Apples are far more nutritious than oranges.</span></p><p><span style=\"color: black;\">D. A small number of US adult apple eaters tend to take less medicine.</span></p><p><span style=\"color: black;\">46. What can we learn from the passage?</span></p><p><span style=\"color: black;\">A. Apples are better than oranges.</span></p><p><span style=\"color: black;\">B. Apples do have some vitamin C to increase the immune system.</span></p><p><span style=\"color: black;\">C. Apples can help cure certain diseases.</span></p><p><span style=\"color: black;\">D. Apples can provide people with sufficient daily intake of energy.</span></p><p><span style=\"color: black;\">47. What can be described as the writing style of this passage?</span></p><p><span style=\"color: black;\">A. Objective. </span></p><p><span style=\"color: black;\">B. Creative.</span></p><p><span style=\"color: black;\">C．subjective</span></p><p><span style=\"color: black;\">D．persuasive</span></p><p class=\"ql-align-center\"><strong style=\"color: black;\">Passage Four</strong></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sometimes I scratch my head when I read about the government\' s efforts to improve schools:new standards and tests to be applied, strict teacher evaluations, and threats of school closures and job losses. They frighten the school employees, not to mention the students. Instead of making people unable to solve problems or try new ideas--which is what fear does to us--research on school reform strongly suggests that policy-makers should encourage school leaders to take a more humane approach. In their study on the reform efforts of twelve Chicago public schools, Bryk and Schneider found that enabling positive social relationships between the adults was the key to successful school improvement and that trust was at the heart of those relationships.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Trust in schools comes down to one thing:psychological safety or safety to speak one\'s mind,to discuss with openness and honesty what is and isn\' t working,to make collective decisions.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Yet this kind of safety doesn\' t come easily to schools. According to Bryk and Schneider, the adults in school rely on each other to do their jobs correctly and with integrity （正直). The challeage is that our expectations are very diverse based on our unique backgrounds.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;At one school where I taught, each teacher had different expectations about how much effort teachers should put into their work--a big difference between the teachers who left af~the last bell and those who worked into the evening. And when expectations are uncoasci or unspoken, it becomes impossible for others to live up to them.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;We also make assumptions about the intentions behind a person\' s behavior. As we all Imam,assumptions are often wrong. For example, parents and teachers my think the principal taml particular decision based on his career advancement rather than hat\" s best for the studeata. don\'t feel psychologically safe to question our assumptions and e~aecmtiatm, trust itiea am the window and our relationships suffer.</span></p><p><span style=\"color: black;\">48. According to Paragraph 1,why does the author scratch his head?</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. Because he doesn\' t know what to do once schools are closed.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;B. Because he is not sure about the practicability of those new tests.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. Because he is concerned that many teachers will lose their jobs.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;D. Because he is not in favor of the government\' s reform efforts.</span></p><p><span style=\"color: black;\">49. According to Bryk and Schneider, what was most important for successful school improvemt?</span></p><p>	<span style=\"color: black;\">A. New standards and tests in schools.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p><p>	<span style=\"color: black;\">B. Positive social relationships.</span></p><p>	<span style=\"color: black;\">C. Strict teacher and student evaluations.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p><p>	<span style=\"color: black;\">D. Assistance of the government.</span></p><p><span style=\"color: black;\">50. What is meant by trust in school?</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. Freedom to express one\' s views,</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;B. Extra effort teachers put into their work.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. Independence of the teachers in schools.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;D. Unconscious and unspoken expectations.</span></p><p><span style=\"color: black;\">51. What does the author say about the assumptions made about the intentions behind a person\'s behavior?</span></p><p>	<span style=\"color: black;\">A. They should be trusted.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p><p>	<span style=\"color: black;\">B. They are often bold.</span></p><p>	<span style=\"color: black;\">C. They are often incorrect.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p><p>	<span style=\"color: black;\">D. They should be encouraged.</span></p><p class=\"ql-align-center\"><strong style=\"color: black;\">Passage Five</strong></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;An interesting project called Blue Zones is recording the lifestyle secrets of the communities with the highest, hest concentrations of </span><u style=\"color: black;\">centenarians</u><span style=\"color: black;\"> in the world.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;The people in the five regions in Europe, Latin America,Asia and the US that live to be 100 have a lot going for them. Genes probably play a small role, but these folks also have strong social ties ,tightly-knit families and lots of opportunities to exercise.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;As we were examining the dietary secrets of the Blue Zones, as described in author Dan Buettner\" s latest book, The Blue Zones Solution, we were struck by how essential tea drinking is in these regions. In fact, Buettner\' s Blue Zones Beverage Rule--a kind of guideline summarized from his 15 or so years of studying these places--is:\" Drink coffee for breakfast, tea in the afternoon, wine at 5 p. tm\"</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;Science has plenty to say about the healthful virtues of green tea. Researchers are most enthusi- astic almt the components in green tea, as well as foods like cocoa. Why might they help so many Okina~vans in Japan break 1007 Some components in green tea can lower the risk of stroke,heart disease attd several cancers. One review study also found that drinking green tea can slightly improve metabolism (新陈代谢).</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;&nbsp;If you find yourself on the island of Ikaria, the Greek Blue Zone in the middle of the Aegean, you won\'t be offered any tea made with tea leaves. Instead, Ikarians typically make their daily cup of tea with just one fresh herb that they have picked themselves that day--either rosemary, wild sage,oregano,nmrjotmn,mint or dandelion,all plants that may have anti-inflammatory (消炎的) properties,</span></p><p><span style=\"color: black;\">which may help lower blood pressure. This could explain Ikaria\' s very low dementia (痴呆) rate,since high blood pressure is a risk factor for the disease.</span></p><p><span style=\"color: black;\">52. What does the underlined word \"centenarians\" in Paragraph 1 refer to?</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. People who have secret lifestyles.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;B. People who enjoy physical exercise.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. People who are one hundred years old or older.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;D. People who carry the gene for being slim.</span></p><p><span style=\"color: black;\">53. According to Paragraph 3 ,what is the recommended time for tea drinking?</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. In the morning.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. Any time of a day.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. In the early evening.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. In the afternoon.</span></p><p><span style=\"color: black;\">54. What may the tea Ikarians drink daily help?</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. To improve metabolism.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. To lower blood pressure.</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;C. To lower life stress.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. To improve social relationships.</span></p><p><span style=\"color: black;\">55. What might be the best title of the passage?</span></p><p><span style=\"color: black;\">&nbsp;&nbsp;&nbsp;A. Tea-Drinking Tips&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B. Lifestyle Secrets of Ikarians&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p><p>	<span style=\"color: black;\">C. Tea-Drinking Ceremony in Okinawa&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D. Blue Zones Solutions</span></p><p><span style=\"color: black;\">Ⅴ.Daily Conversation ( 15 points)</span></p><p><span style=\"color: black;\">Directions:Pick out appropriate expressions from the eight choices below and complete thefollowing dialoaue by blackenina the corresuondina letter on the Answer Sheet.</span></p><p><img src=\"https://i.loli.net/2018/10/09/5bbc543bd62e6.png\" width=\"559\" height=\"140\"></p><p><span style=\"color: black;\">Woman : Hello, Mr. Johnson\' s office.</span></p><p><span style=\"color: black;\">Man : Good morning.</span><u style=\"color: black;\">&nbsp;56 </u><span style=\"color: black;\">？</span></p><p><span style=\"color: black;\">Woman : Sorry,he\' s in a meeting at the moment.</span><u style=\"color: black;\">&nbsp;&nbsp;57&nbsp;</u><span style=\"color: black;\">？</span></p><p><span style=\"color: black;\">Man:Yes. This is Steve Lee from Brightlight Systems. </span><u style=\"color: black;\">&nbsp;58 </u><span style=\"color: black;\">？</span></p><p><span style=\"color: black;\">Woman:Tomorrow afternoon in your office.</span></p><p><span style=\"color: black;\">Man : </span><u style=\"color: black;\">&nbsp;59</u></p><p><span style=\"color: black;\">Woman : Okay.&nbsp;</span><u style=\"color: black;\">&nbsp;60</u></p><p><span style=\"color: black;\">Man : Thank you.</span></p><p><span style=\"color: black;\">第Ⅱ卷(非选择题，共25分)</span></p><p><span style=\"color: black;\">Ⅵ. Writing ( 25 points)</span></p><p><span style=\"color: black;\">Directions:For this part, you are supposed to write an essay in English in 100 - 120 words based on the following information. Remember to write it clearly.</span></p><p><span style=\"color: black;\">61．你(Li Yuan)组织同学进行了一次烧烤野餐(barbecue)。请给你的英国朋友Tim写一封电子邮件，内容包括：</span></p><p><span style=\"color: black;\">&nbsp;·野餐前的准备；</span></p><p><span style=\"color: black;\">&nbsp;·野餐过程；</span></p><p><span style=\"color: black;\">&nbsp;·印象最深刻的人或事。</span></p>','admin','','2018-10-09 15:10:32');

/*Table structure for table `com_image` */

DROP TABLE IF EXISTS `com_image`;

CREATE TABLE `com_image` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) DEFAULT NULL,
  `storename` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `hash` varchar(255) DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL,
  `width` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

/*Data for the table `com_image` */

insert  into `com_image`(`id`,`filename`,`storename`,`url`,`hash`,`height`,`width`,`size`,`status`,`ts`) values (66,'acb79be0-c187-11e8-bd12-c93f44788637','5bab7b6b77001.png','https://i.loli.net/2018/09/26/5bab7b6b77001.png','DYQ1LrCH5aEc8pj','200','200','47736','','2018-09-26 20:28:31'),(67,'c25b01d0-c187-11e8-bd12-c93f44788637','5bab7b902705b.png','https://i.loli.net/2018/09/26/5bab7b902705b.png','TIu4mgKaULb2esE','200','200','98022','','2018-09-26 20:29:07'),(68,'db819b10-c187-11e8-bd12-c93f44788637','5bab7bbae0cf3.png','https://i.loli.net/2018/09/26/5bab7bbae0cf3.png','NnQHX9hyM1ptBOg','200','200','108156','','2018-09-26 20:29:50'),(69,'ec2f51f0-c187-11e8-bd12-c93f44788637','5bab7bd695335.png','https://i.loli.net/2018/09/26/5bab7bd695335.png','U8O3156uFZxgteS','200','200','74387','','2018-09-26 20:30:18'),(70,'f1ef55e0-c187-11e8-bd12-c93f44788637','5bab7be1829ae.png','https://i.loli.net/2018/09/26/5bab7be1829ae.png','TSaqybVXndvK643','200','200','111285','','2018-09-26 20:30:29'),(71,'0624d3f0-c188-11e8-bd12-c93f44788637','5bab7c028b452.png','https://i.loli.net/2018/09/26/5bab7c028b452.png','nVvRDXod2OMuNPs','200','200','74969','','2018-09-26 20:31:02'),(72,'1b3ce9d0-c188-11e8-bd12-c93f44788637','5bab7c2622b0c.png','https://i.loli.net/2018/09/26/5bab7c2622b0c.png','k6ZnWyCKVHothRm','200','200','132565','','2018-09-26 20:31:37'),(73,'36dfa6a0-c188-11e8-bd12-c93f44788637','5bab7c53bead8.png','https://i.loli.net/2018/09/26/5bab7c53bead8.png','CXKHP6ApkjNngxf','200','200','119751','','2018-09-26 20:32:23'),(74,'4de75780-c188-11e8-bd12-c93f44788637','5bab7c7aa6fd0.png','https://i.loli.net/2018/09/26/5bab7c7aa6fd0.png','DQwnRK8pudPjNc1','200','200','91106','','2018-09-26 20:33:02'),(75,'9bffb230-c18f-11e8-bd12-c93f44788637','5bab88bc82a2d.png','https://i.loli.net/2018/09/26/5bab88bc82a2d.png','3GWUM2zrfnDFlkC','200','200','111510','','2018-09-26 21:25:20'),(76,'780844c0-c1fb-11e8-b60d-499a1785edca','5bac3e070fca5.png','https://i.loli.net/2018/09/27/5bac3e070fca5.png','sGzbyYIpTrvZg4w','506','843','996040','','2018-09-27 10:18:50'),(77,'63004490-c234-11e8-8d49-a769929428bc','5bac9d323bd9b.png','https://i.loli.net/2018/09/27/5bac9d323bd9b.png','toMUnZ1gV7OFebs','426','426','353303','','2018-09-27 17:04:54'),(78,'64003d80-c236-11e8-a716-a3561a4e523d','5baca08d7e0fa.png','https://i.loli.net/2018/09/27/5baca08d7e0fa.png','ibjNgI35xEZRXvz','125','125','43446','','2018-09-27 17:19:13'),(79,'1f194620-c237-11e8-9163-4d75123c6fa3','5baca1c8833d7.png','https://i.loli.net/2018/09/27/5baca1c8833d7.png','fsc5H4VUQmyIezr','287','287','197188','','2018-09-27 17:24:28'),(80,'44baee00-c2c4-11e8-9747-415ca94babc2','5bad8e9899325.png','https://i.loli.net/2018/09/28/5bad8e9899325.png','XgISyMa5cGrZK2s','1638','1092','531409','','2018-09-28 10:14:51'),(81,'4b165a60-cb92-11e8-a6d3-3770444b5e02','5bbc543bd62e6.png','https://i.loli.net/2018/10/09/5bbc543bd62e6.png','auh9XQxLeYMK74n','407','1621','625921','','2018-10-09 15:09:48');

/*Table structure for table `qtz_blob_triggers` */

DROP TABLE IF EXISTS `qtz_blob_triggers`;

CREATE TABLE `qtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_blob_triggers` */

/*Table structure for table `qtz_calendars` */

DROP TABLE IF EXISTS `qtz_calendars`;

CREATE TABLE `qtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_calendars` */

/*Table structure for table `qtz_cron_triggers` */

DROP TABLE IF EXISTS `qtz_cron_triggers`;

CREATE TABLE `qtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_cron_triggers` */

/*Table structure for table `qtz_fired_triggers` */

DROP TABLE IF EXISTS `qtz_fired_triggers`;

CREATE TABLE `qtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_fired_triggers` */

/*Table structure for table `qtz_job_details` */

DROP TABLE IF EXISTS `qtz_job_details`;

CREATE TABLE `qtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_job_details` */

/*Table structure for table `qtz_locks` */

DROP TABLE IF EXISTS `qtz_locks`;

CREATE TABLE `qtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_locks` */

insert  into `qtz_locks`(`SCHED_NAME`,`LOCK_NAME`) values ('scheduler','STATE_ACCESS'),('scheduler','TRIGGER_ACCESS');

/*Table structure for table `qtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `qtz_paused_trigger_grps`;

CREATE TABLE `qtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_paused_trigger_grps` */

/*Table structure for table `qtz_scheduler_state` */

DROP TABLE IF EXISTS `qtz_scheduler_state`;

CREATE TABLE `qtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_scheduler_state` */

insert  into `qtz_scheduler_state`(`SCHED_NAME`,`INSTANCE_NAME`,`LAST_CHECKIN_TIME`,`CHECKIN_INTERVAL`) values ('scheduler','CJWX-PC1539064719867',1539077274140,20000);

/*Table structure for table `qtz_simple_triggers` */

DROP TABLE IF EXISTS `qtz_simple_triggers`;

CREATE TABLE `qtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_simple_triggers` */

/*Table structure for table `qtz_simprop_triggers` */

DROP TABLE IF EXISTS `qtz_simprop_triggers`;

CREATE TABLE `qtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_simprop_triggers` */

/*Table structure for table `qtz_triggers` */

DROP TABLE IF EXISTS `qtz_triggers`;

CREATE TABLE `qtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_triggers` */

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resourcecode` varchar(50) DEFAULT NULL,
  `resourcename` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` char(2) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `ico` varchar(255) DEFAULT NULL,
  `level` int(10) DEFAULT NULL,
  `parentid` int(20) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`id`,`resourcecode`,`resourcename`,`description`,`type`,`url`,`ico`,`level`,`parentid`,`status`,`ts`) values (1,'system','系统管理','系统管理','1','','navicon-round',1,0,'',NULL),(11,'user','用户管理','系统管理-用户管理','2',NULL,'person',2,1,'',NULL),(12,'role','角色管理','系统管理-角色管理','2','/system/role/list','person-stalker',2,1,'',NULL),(13,'resource','资源管理','系统管理-资源管理','2','/system/resource/list','ios-keypad',2,1,'',NULL),(18,'monitor','系统监控','系统监控','1',NULL,'navicon-round',1,0,'',NULL),(27,'thread','线程管理','系统监控-线程管理','2','/system/thread','ionic',2,18,'',NULL),(20,'tomcat','TOMCAT','系统监控-TOMCAT','2','/system/chgpwd','ios-color-filter',2,18,'',NULL),(26,'redis','REDIS','系统监控-REDIS','2','/system/crawler','help-buoy',2,18,'',NULL),(28,'memory','内存管理','系统监控-内存管理','2','/system/memory','ios-pie',2,18,'',NULL),(33,'ROLE_LIST','角色列表','系统管理-角色管理-列表','3','/system/role/list',NULL,0,12,'',NULL),(29,'USER_LIST','用户列表','系统管理-用户管理-列表','3','/system/user/list',NULL,0,11,'',NULL),(30,'USER_STATUS','用户状态','系统管理-用户管理-状态','3','/system/user/status',NULL,0,11,'',NULL),(31,'USER_CREATE','用户新增','系统管理-用户管理-新增','3','/system/user/create',NULL,0,11,'',NULL),(34,'USER_REMOVE','用户删除','系统管理-用户管理-删除','3','/system/user/remove',NULL,0,11,'',NULL),(35,'USER_PASSWORD','用户密码','系统管理-用户管理-密码','3','/system/user/password',NULL,0,11,'',NULL),(36,'USER_EDIT','用户编辑','系统管理-用户管理-编辑','3','/system/user/edit',NULL,0,11,'',NULL),(37,'ROLE_CREATE','角色新增','系统管理-角色管理-新增','3','/system/role/create',NULL,0,12,'',NULL),(38,'ROLE_EDIT','角色编辑','系统管理-角色管理-编辑','3','/system/role/edit',NULL,0,12,'',NULL),(39,'ROLE_STATUS','角色状态','系统管理-角色管理-状态','3','/system/role/status',NULL,0,12,'',NULL),(40,'ROLE_REMOVE','角色删除','系统管理-角色管理-删除','3','/system/role/remove',NULL,0,12,'',NULL),(41,'ROLE_AVAILABLE','角色全部','系统管理-角色管理-全部','3','/system/role/availablelist',NULL,0,12,'',NULL),(42,'RESOURCE_LIST','资源列表','系统管理-资源管理-列表','3','/system/resource/list',NULL,0,13,'',NULL),(43,'RESOURCE_CREATE','资源创建','系统管理-资源管理-新增','3','/system/resource/create',NULL,0,13,'',NULL),(44,'RESOURCE_EDIT','资源编辑','系统管理-资源管理-编辑','3','/system/resource/edit',NULL,0,13,'',NULL),(45,'RESOURCE_STATUS','资源状态','系统管理-资源管理-状态','3','/system/resource/status',NULL,0,13,'',NULL),(46,'RESOURCE_REMOVE','资源删除','系统管理-资源管理-删除','3','/system/resource/remove',NULL,0,13,'',NULL),(47,'RESOURCE_AVAILABLE','资源全部','系统管理-资源管理-全部','3','/system/resource/availablelist',NULL,0,13,'',NULL),(48,'quartz','定时任务','定时任务','1',NULL,'navicon-round',0,0,'',NULL),(49,'jobdetail','任务管理','定时任务-任务管理','2',NULL,'ios-fastforward',0,48,'',NULL),(50,'trigger','触发器管理','定时任务-触发器管理','2',NULL,'ios-time',0,48,'',NULL),(51,'crawler','网页爬虫','网页爬虫','1',NULL,'navicon-round',0,0,'','2018-08-13 17:24:43'),(52,'crawler','爬虫管理','网页爬虫-爬虫管理','2',NULL,'bug',0,51,'','2018-08-13 17:26:17'),(53,'weburl','页面管理','网页爬虫-页面管理','2',NULL,'link',0,51,'','2018-08-15 10:28:58'),(54,'image','图片管理','公共维护-图片管理','2',NULL,'image',0,55,'','2018-09-15 13:55:23'),(55,'common','公共维护','公共维护','1',NULL,'navicon-round',0,0,'','2018-09-27 20:23:42'),(56,'article','文章管理','公共维护-文章管理','2',NULL,'compose',2,55,'','2018-09-27 20:33:17');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolecode` varchar(50) DEFAULT NULL,
  `rolename` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `resourceids` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=390 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`rolecode`,`rolename`,`description`,`resourceids`,`status`,`ts`) values (177,'manager','世界政府','维护世界秩序','12,13,11,20,26,28,1,18,27,33,29,30,44,42,41,40,39,38,37,36,35,34,31,46,47,45,43,48,49,50,51,52,53,55,54,56','',NULL),(179,'White Beard Pirates','白胡子海贼团','白胡子海贼团','11,12,13,20,26,28,29,1,37,38','',NULL),(11,'Big Mom Pirates','BIG·MOM海贼团','BIG·MOM海贼团','12,11,13,28,26,20,29,30,37,43','',NULL),(12,'Beasts Pirates','百兽海贼团','凯多海贼团','12,13,11,20,26,28','',NULL),(14,'Red Hair Pirates','红发海贼团','红发海贼团','31,34,35,36,29,30,11,1,12,33,37,38,39,40,41,42,43,44,45,46,47,13','',NULL),(16,'Black Beard Pirates','黑胡子海贼团','监控','12,13,28,26,20,11,29,30,31,34,35,36','',NULL),(17,'marine','海军','管理','12,13','',NULL),(386,'test','测试','<p><a href=\"https://i.loli.net/2018/09/26/5bab7b902705b.png\" target=\"_blank\" style=\"background-color: rgb(230, 0, 0);\">啊啊撒2222</a></p>','11,1,29,12,33,27,18,20,13','',NULL);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usercode` varchar(50) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `salt` varchar(32) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `mobile` char(12) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `lastlogintime` datetime DEFAULT NULL,
  `roleids` text,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`usercode`,`username`,`password`,`salt`,`sex`,`birthday`,`mobile`,`email`,`imgurl`,`lastlogintime`,`roleids`,`status`,`ts`) values (3,'namei','娜美','79722ca3f790560d2b849ad1f824ab65','ccdb4f2b724114a70db344001a3d16ad','F','1970-01-30 08:00:00','12345678910',NULL,'https://i.loli.net/2018/09/26/5bab7bd695335.png','2018-09-25 19:56:24','14','',NULL),(4,'qiaoba','乔巴','79722ca3f790560d2b849ad1f824ab65','ccdb4f2b724114a70db344001a3d16ad','M','1970-01-08 08:00:00','12345678910',NULL,'https://i.loli.net/2018/09/26/5bab7be1829ae.png','2018-09-26 14:53:44','14','',NULL),(2,'suolo','索隆','79722ca3f790560d2b849ad1f824ab65','ccdb4f2b724114a70db344001a3d16ad','M','1970-01-15 08:00:00','12345678910',NULL,'https://i.loli.net/2018/09/26/5bab7b902705b.png','2018-09-25 19:55:41','14','',NULL),(1,'lufei','路飞','ec831849e9d39d487319df8b09c589e1','135576a35439731172a73eae0fd080ae','M','2010-01-20 08:00:00','12345678910',NULL,'https://i.loli.net/2018/09/26/5bab7bbae0cf3.png','2018-09-28 20:12:32','14','',NULL),(9,'admin','系统管理员','49962d5181aa4adcd4786938cff39bb8','f97baedff1fa57022779dc5438d5227a','M','1991-03-30 08:00:00','15250452118','991013842@qq.com','https://i.loli.net/2018/09/26/5bab7b6b77001.png','2018-10-09 17:26:03','177','',NULL),(39,'luo','罗','65a71b0517b4580c0940d0d553373826','c5db56eacd16873ee3876c8ff36dc247','M','2018-07-02 08:00:00','12345678910',NULL,'https://i.loli.net/2018/09/26/5bab7c2622b0c.png','2018-09-19 15:00:56','12','',NULL),(40,'test2','测试','34598373f24ddaaef1cb4c626f653e18','a0c617dd58d45c3f32f16b0b2f063743','M','2018-07-12 08:00:00','23211111111',NULL,'https://i.loli.net/2018/09/26/5bab7c53bead8.png',NULL,'386','\0',NULL),(42,'ace','艾斯','6b6bf0c38e1b989e677c2613c66a4d95','c93ffbcc6bee8b61939761ee22a45226','M','2018-09-10 08:00:00',NULL,NULL,'https://i.loli.net/2018/09/26/5bab7c028b452.png',NULL,'179','','2018-09-12 16:24:34'),(43,'testccc','cess','b8d73fbe8412fe630dd4690a8cc0c164','a704116cfd098f71704a34dbe0d13b5a','M','2015-03-27 08:00:00','weqeqeq','31313qeq','https://i.loli.net/2018/09/26/5bab88bc82a2d.png',NULL,'386','','2018-09-17 13:54:53'),(44,'vvvvvvvvv2','dadas','d58c0c63bbf9d13e964acde05c00fbcd','3d57077379bdcca2fa2455a35212b07d',NULL,NULL,'123123131','edada2','https://i.loli.net/2018/09/26/5bab7c7aa6fd0.png',NULL,'386','\0','2018-09-17 13:55:10'),(45,'gggg5gggg','dasda','5234728928165e7ec59a2e001791915a','9401089074b92c06e1bf616a42e6809b','M',NULL,'31313132','qeqe','https://i.loli.net/2018/09/26/5bab34b4d7ef5.png',NULL,'386','','2018-09-17 13:55:22'),(46,'test','对对对','4b2e42c8e801206ba08a92022285d4cf','678b0d6ac3f557851957bab0fa4af90c','M',NULL,'而我却二群无',NULL,NULL,NULL,'177,179,11,12,14,16,17,386','','2018-09-29 13:19:16');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

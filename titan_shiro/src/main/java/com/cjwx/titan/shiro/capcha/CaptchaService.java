package com.cjwx.titan.shiro.capcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

import javax.servlet.http.HttpServletRequest;

import com.jhlabs.image.WaterFilter;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.LineTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;


public class CaptchaService {
	
    public static DefaultManageableImageCaptchaService captchaService;

    static { 
        WaterFilter water = new WaterFilter();  
        water.setAmplitude(3d);  
        water.setAntialias(true);  
        water.setPhase(20d);  
        water.setWavelength(70d); 
    	
        ImageDeformation backDef = new ImageDeformationByFilters(new ImageFilter[]{});  
        ImageDeformation textDef = new ImageDeformationByFilters(new ImageFilter[]{});  
        ImageDeformation postDef = new ImageDeformationByFilters(new ImageFilter[]{water});
    	
    	RandomWordGenerator wordgen =  new RandomWordGenerator("0123456789abcdefghijklmnopqrstuvwxyz");
    	RandomRangeColorGenerator colors = new RandomRangeColorGenerator(new int[]{0,150},new int[]{0,150},new int[]{0,150});
        RandomRangeColorGenerator lineColors = new RandomRangeColorGenerator(new int[]{150,255},new int[]{150,255},new int[]{150,255});
    	Font[] fonts = new Font[] {new Font("Arial", 0, 15),  
                	new Font("Tahoma", 0, 15), new Font("Verdana", 0, 15),  
                	new Font("Helvetica", 0, 15), new Font("宋体", 0, 15),  
                	new Font("黑体", 0, 15), new Font("幼圆", 0, 15)}; 
    	
    	//干扰线
    	TextDecorator[] baffleDecorators = new TextDecorator[]{new LineTextDecorator(1,lineColors)};
    	TextPaster randomPaster = new DecoratedRandomTextPaster(4,4,colors,null);
    	
    	UniColorBackgroundGenerator backGenUni = new UniColorBackgroundGenerator(105,34,Color.white);
    	
    	FontGenerator fontGenRandom = new RandomFontGenerator(20,0,fonts);
    	
    	WordToImage wordtoimage = new DeformedComposedWordToImage(fontGenRandom,backGenUni,randomPaster,backDef,textDef,postDef);
    	
    	GimpyFactory captchaFactory = new GimpyFactory(wordgen,wordtoimage);
    	
    	GenericCaptchaEngine engine = new GenericCaptchaEngine(new GimpyFactory[]{captchaFactory});
        captchaService = new DefaultManageableImageCaptchaService (new FastHashMapCaptchaStore(),engine,180,100000, 75000);
	}

    public static boolean validateResponse(HttpServletRequest request, String Captcha){  
    	if (request.getSession(false) == null){
    		return false;
    	}  
    	boolean validated = false;  
    	try {  
    		String id = request.getSession().getId();  
    		validated = captchaService.validateResponseForID(id, Captcha).booleanValue();  
    	} catch (CaptchaServiceException e) {  
    		e.printStackTrace();  
    	}  
    	return validated;  
    }   
}

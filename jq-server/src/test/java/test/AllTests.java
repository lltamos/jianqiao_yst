package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alqsoft.entity.DoctorImage;
import com.alqsoft.service.doctorimage.DoctorImageService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:/applicationContext.xml")
public class AllTests {
	
	
	@Autowired
	private DoctorImageService doctorImageService;
	
	/*@Test
	public void test01(){
		DoctorImage doctorImage = doctorImageService.findById(1);
		if(doctorImage!=null){
			System.out.println(doctorImage.getDoctorName()+"===========");
		}else{
			System.out.println("无数据");
		}
	}*/
	
}

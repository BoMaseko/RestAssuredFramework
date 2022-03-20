package org.bongz.constants;

import lombok.Getter;

public final class FrameworkConstants {
	
	private FrameworkConstants() {}
	
	private static final String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/resources";
	private @Getter static final String requestFolderPath = RESOURCESPATH + "/requestbody/";
	private @Getter static final String baseURI = "https://multiplyaipre.multiply.co.za";
	private @Getter static final String excelFolderPath = RESOURCESPATH + "/Excel/Data.xlsx";
	private @Getter static final String configPropertyFilePath = RESOURCESPATH + "/config/config.properties";
	private @Getter static final String extentReportFolderPath = RESOURCESPATH + "/extent-output-report/index.html";
	public static final int STATUSCODE200 = 200;
	
	
	public static final String RUNMANAGERSHEET = "RunManager";
	public static final String ITERATIONDATASHEET = "IterationData"; 
	public static final String BOLD_START = "<b>";
    public static final String BOLD_END = "</b>";
    public static final String ICON_SMILEY_PASS = "<i class='fa fa-smile-o' style='font-size:24px'></i>";
    public static final String ICON_SMILEY_SKIP = "<i class=\"fas fa-frown-open\"></i>";
    public static final String ICON_SMILEY_FAIL = "<i class='fa fa-frown-o' style='font-size:24px'></i>";
    public static final String ICON_BUG = "<i class='fa fa-bug' ></i>";

	

}

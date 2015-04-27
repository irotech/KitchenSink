Ehcache

VM Option or external config file
	-Djava.io.tmpdir="C:\tmp"

Configuration file
<project-root>\src\main\config\ehcache.xml
	<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd">
		<diskStore path="java.io.tmpdir" />
		<cache name="com.gmail.at.gmail.project.package.object" 
			maxElementsInMemory="100"
            eternal="true"
            maxElementsOnDisk="200"
            diskPersistent="false"
            diskExpiryThreadIntervalSeconds="120"
            memoryStoreEvictionPolicy="LRU">
		</cache>
		<defaultCache
            maxElementsInMemory="1000"
            eternal="true"
            maxElementsOnDisk="1000"
            diskPersistent="false"
            diskExpiryThreadIntervalSeconds="120"
            memoryStoreEvictionPolicy="LRU">
		</defaultCache>
	</ehcache>

Log4j Logger
Avoid DefaultSizeOfEngine using [Agent, Unsafe, Reflection] sizeof engine log lines
<project-root>\src\main\config\log4j-local.xml
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
	<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/" debug="true">
		<appender name="SystemOut" class="org.apache.log4j.ConsoleAppender">
			<param name="Target" value="System.out"/>
			<layout class="org.apache.log4j.PatternLayout">
				<param name="ConversionPattern" value="%d %-5p [%-20.20c{1}][%-20.20t] %m%n" />
			</layout>
		</appender>
		<appender name="CustomLog" class="com.gmail.at.gmail.project.package.AdvancedDailyRollingFileAppender">
	        <param name="File" value="${catalina.base}/logs/fe-dailylog.log" />
	        <param name="datePattern" value="'.'yyyy-MM-dd" />
	        <param name="ImmediateFlush" value="true"/>
	        <param name="maxNumberOfBackups" value="30"/>
	       <layout class="org.apache.log4j.PatternLayout">
	            <param name="ConversionPattern" value="%d %-5p [%-25.25c{1}][%-31.31t] %m%n" />
	        </layout>
	    </appender>
	    <logger name="net.sf.ehcache.pool.impl.DefaultSizeOfEngine" additivity="false">
	        <level value="OFF" />
	    </logger>
	    <root>
			<priority value="INFO"/>
			<appender-ref ref="SystemOut" />
			<appender-ref ref="CustomLog" />
		</root>
	</log4j:configuration>

#ToBeMovedStart
com.gmail.at.gmail.project.package.commons.logging.AdvancedDailyRollingFileAppender

	com.gmail.at.gmail.project.package.commons.logging;

	import java.io.File;
	import java.io.FilenameFilter;
	import java.io.IOException;
	import java.io.InterruptedIOException;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Date;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Locale;
	import java.util.TimeZone;
	import org.apache.log4j.FileAppender;
	import org.apache.log4j.Layout;
	import org.apache.log4j.helpers.LogLog;
	import org.apache.log4j.spi.LoggingEvent;
	import uk.co.and.commons.logging.ModifiedTimeSortableFile;
	import uk.co.and.commons.logging.RollingCalendar;

	public class AdvancedDailyRollingFileAppender extends FileAppender {
	    static final int TOP_OF_TROUBLE = -1;
	    static final int TOP_OF_MINUTE = 0;
	    static final int TOP_OF_HOUR = 1;
	    static final int HALF_DAY = 2;
	    static final int TOP_OF_DAY = 3;
	    static final int TOP_OF_WEEK = 4;
	    static final int TOP_OF_MONTH = 5;
	    private String datePattern = "\'.\'yyyy-MM-dd";
	    protected int maxNumberOfBackups = 1;
	    private String scheduledFilename;
	    private long nextCheck = System.currentTimeMillis() - 1L;
	    Date now = new Date();
	    SimpleDateFormat sdf;
	    RollingCalendar rc = new RollingCalendar();
	    int checkPeriod = -1;
	    static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");

	    public AdvancedDailyRollingFileAppender() {
	    }

	    public AdvancedDailyRollingFileAppender(Layout layout, String filename, String datePattern) throws IOException {
	        super(layout, filename, true);
	        this.datePattern = datePattern;
	        this.activateOptions();
	    }

	    public void setDatePattern(String pattern) {
	        this.datePattern = pattern;
	    }

	    public void setMaxNumberOfBackups(int maxBackups) {
	        this.maxNumberOfBackups = maxBackups;
	    }

	    public int getMaxNumberOfBackups() {
	        return this.maxNumberOfBackups;
	    }

	    public String getDatePattern() {
	        return this.datePattern;
	    }

	    public void activateOptions() {
	        super.activateOptions();
	        if(this.datePattern != null && this.fileName != null) {
	            this.now.setTime(System.currentTimeMillis());
	            this.sdf = new SimpleDateFormat(this.datePattern);
	            int type = this.computeCheckPeriod();
	            this.printPeriodicity(type);
	            this.rc.setType(type);
	            File file = new File(this.fileName);
	            this.scheduledFilename = this.fileName + this.sdf.format(new Date(file.lastModified()));
	        } else {
	            LogLog.error("Either File or DatePattern options are not set for appender [" + this.name + "].");
	        }
	    }

	    void printPeriodicity(int type) {
	        switch(type) {
	        case 0:
	            LogLog.debug("Appender [" + this.name + "] to be rolled every minute.");
	            break;
	        case 1:
	            LogLog.debug("Appender [" + this.name + "] to be rolled on top of every hour.");
	            break;
	        case 2:
	            LogLog.debug("Appender [" + this.name + "] to be rolled at midday and midnight.");
	            break;
	        case 3:
	            LogLog.debug("Appender [" + this.name + "] to be rolled at midnight.");
	            break;
	        case 4:
	            LogLog.debug("Appender [" + this.name + "] to be rolled at start of week.");
	            break;
	        case 5:
	            LogLog.debug("Appender [" + this.name + "] to be rolled at start of every month.");
	            break;
	        default:
	            LogLog.warn("Unknown periodicity for appender [" + this.name + "].");
	        }
	    }

	    int computeCheckPeriod() {
	        RollingCalendar rollingCalendar = new RollingCalendar(gmtTimeZone, Locale.getDefault());
	        Date epoch = new Date(0L);
	        if(this.datePattern != null) {
	            for(int i = 0; i <= 5; ++i) {
	                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
	                simpleDateFormat.setTimeZone(gmtTimeZone);
	                String r0 = simpleDateFormat.format(epoch);
	                rollingCalendar.setType(i);
	                Date next = new Date(rollingCalendar.getNextCheckMillis(epoch));
	                String r1 = simpleDateFormat.format(next);
	                if(r0 != null && r1 != null && !r0.equals(r1)) {
	                    return i;
	                }
	            }
	        }
	        return -1;
	    }

	    void rollOver() throws IOException {
	        List files = this.getAllFiles();
	        Collections.sort(files);
	        if(files.size() >= this.maxNumberOfBackups) {
	            int datedFilename = 0;
	            int target = files.size() - (this.maxNumberOfBackups - 1);
	            for(Iterator file = files.iterator(); file.hasNext(); ++datedFilename) {
	                ModifiedTimeSortableFile result = (ModifiedTimeSortableFile)file.next();
	                if(datedFilename >= target) {
	                    break;
	                }
	                result.delete();
	            }
	        }
	        if(this.datePattern == null) {
	            this.errorHandler.error("Missing DatePattern option in rollOver().");
	        } else {
	            LogLog.debug("maxNumberOfBackups=" + this.maxNumberOfBackups);
	            String var8 = this.fileName + this.sdf.format(this.now);
	            if(!this.scheduledFilename.equals(var8)) {
	                this.closeFile();
	                File var9 = new File(this.scheduledFilename);
	                if(var9.exists()) {
	                    var9.delete();
	                }
	                File var10 = new File(this.fileName);
	                boolean var11 = var10.renameTo(var9);
	                if(var11) {
	                    LogLog.debug(this.fileName + " -> " + this.scheduledFilename);
	                } else {
	                    LogLog.error("Failed to rename [" + this.fileName + "] to [" + this.scheduledFilename + "].");
	                }
	                try {
	                    this.setFile(this.fileName, true, this.bufferedIO, this.bufferSize);
	                } catch (IOException var7) {
	                    this.errorHandler.error("setFile(" + this.fileName + ", true) call failed.");
	                }
	                this.scheduledFilename = var8;
	            }
	        }
	    }

	    protected void subAppend(LoggingEvent event) {
	        long n = System.currentTimeMillis();
	        if(n >= this.nextCheck) {
	            this.now.setTime(n);
	            this.nextCheck = this.rc.getNextCheckMillis(this.now);
	            try {
	                this.rollOver();
	            } catch (IOException var5) {
	                if(var5 instanceof InterruptedIOException) {
	                    Thread.currentThread().interrupt();
	                }

	                LogLog.error("rollOver() failed.", var5);
	            }
	        }
	        super.subAppend(event);
	    }

	    private List<ModifiedTimeSortableFile> getAllFiles() {
	        ArrayList files = new ArrayList();
	        File file = new File(this.fileName);
	        String parentDirectory = file.getParent();
	        FilenameFilter filter = new FilenameFilter() {
	            public boolean accept(File dir, String name) {
	                String directoryName = dir.getPath();
	                LogLog.debug("directory name: " + directoryName);
	                File file = new File(AdvancedDailyRollingFileAppender.this.fileName);
	                String parentDirectory = file.getParent();
	                if(parentDirectory != null) {
	                    String localFile = AdvancedDailyRollingFileAppender.this.fileName.substring(directoryName.length() + 1);
	                    return name.startsWith(localFile);
	                } else {
	                    return name.startsWith(AdvancedDailyRollingFileAppender.this.fileName);
	                }
	            }
	        };
	        File dir = new File(parentDirectory);
	        String[] names = dir.list(filter);
	        for(int i = 0; i < names.length; ++i) {
	            files.add(new ModifiedTimeSortableFile(dir + System.getProperty("file.separator") + names[i]));
	        }
	        return files;
	    }
	}

#ToBeMovedEnd
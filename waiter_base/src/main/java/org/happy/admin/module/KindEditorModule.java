package org.happy.admin.module;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.happy.admin.model.Img;
import org.happy.base.util.MyUtils;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Streams;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

@IocBean
@At("/admin/KindEditor")
public class KindEditorModule {

	@Inject
	Dao dao;

	@Inject
	MyUtils utils;

	@At
	@AdaptBy(type = UploadAdaptor.class, args = { "ioc:imgUpload" })
	@Ok("json")
	public Object upload_json(@Param("imgFile") TempFile f, HttpServletRequest req, HttpServletResponse resp)
			throws Exception, IOException {
		Record ret = new Record();
		if (f != null) {
			BufferedImage sourceImg = ImageIO.read(new FileInputStream(f.getFile()));
			Img konimg = new Img();
			konimg.height = sourceImg.getHeight();
			konimg.width = sourceImg.getWidth();
			konimg.size = f.getFile().length();
			String filename = f.getMeta().getFileLocalName();
			filename = filename.substring(filename.lastIndexOf("."), filename.length());
			filename = System.nanoTime() + filename;
			konimg.path = f.getFile().getAbsolutePath();
			konimg.data = Streams.readBytes(new FileInputStream(f.getFile()));
			konimg = dao.insert(konimg);

			ret.set("error", 0);
			ret.set("title", f.getMeta().getFileLocalName());
			ret.set("url", utils.getScheme() + "/admin/KindEditor/img?id=" + konimg.id);

		}
		return ret;
	}

	@At
	@Ok("raw")
	public Object img(int id) throws IOException {
		Img img = dao.fetch(Img.class, id);
		BufferedImage sourceImg = ImageIO.read(Streams.wrap(img.data));
		return sourceImg;
	}

	@At
	@Ok("json")
	public Object file_manager_json() {
		List<Img> mlist = dao.query(Img.class, null, null);
		Record ret = new Record();
		ret.put("current_dir_path", "");
		ret.put("total_count", mlist.size());
		ret.put("moveup_dir_path", "");
		ret.put("current_url", utils.getScheme() + "/admin/KindEditor/img?id=");
		List<Record> file_list = new ArrayList<Record>();
		for (int i = 0; i < mlist.size(); i++) {
			Record ccc = new Record();
			ccc.put("filesize", mlist.get(i).size);
			ccc.put("is_dir", false);
			ccc.put("is_photo", true);
			ccc.put("has_file", false);
			ccc.put("filename", mlist.get(i).id);
			ccc.put("datetime", new Date());
			file_list.add(ccc);
		}
		ret.put("file_list", file_list);
		return ret;
	}

}

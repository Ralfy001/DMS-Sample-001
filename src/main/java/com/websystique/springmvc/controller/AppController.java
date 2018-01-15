package com.websystique.springmvc.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.websystique.springmvc.model.Album;
import com.websystique.springmvc.model.FileBucket;
import com.websystique.springmvc.model.Foto;
import com.websystique.springmvc.service.AlbumService;
import com.websystique.springmvc.service.FotoService;
import com.websystique.springmvc.util.FileValidator;


@Controller
@RequestMapping("/")
public class AppController {

  @Autowired
  AlbumService albumService;

  @Autowired
  FotoService fotoService;

  @Autowired
  MessageSource messageSource;

  @Autowired
  FileValidator fileValidator;

  @InitBinder("fileBucket")
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(fileValidator);
  }

  /**
   * This method will display the startingpage.
   */

  @RequestMapping(value = { "/", "/start" }, method = RequestMethod.GET)
  public String showStartingPage(ModelMap model) {

//    for (int i = 0; i < albumService.findAllAlbums().size(); i++) {
//      List<Foto> foto = fotoService.findAllByAlbumId(i);
//      for (int d = 0; d < foto.size(); d++) {
//        byte[] encodeBase64 = Base64.encode(foto.get(d).getContent()).getBytes();
//       String base64Encoded = new String(encodeBase64, "UTF-8");
//        model.addAttribute("fotoid",base64Encoded);
//      }
//    }
//    List<Album> albums = albumService.findAllAlbums();
//    model.addAttribute("albums", albums);
//    //return "albumslist";
    return "startingpage";
  }

  /**
   * This method will list all existing albums.
   */

  @RequestMapping(value = { "/list" }, method = RequestMethod.GET)
  public String listAlbums(ModelMap model) throws UnsupportedEncodingException {

    List<Album> albums = albumService.findAllAlbums();
    model.addAttribute("albums", albums);

	HashMap<Album, List<?>> gallery = new HashMap<Album, List<?>>();
    for (int i = 0; i < albums.size(); i++) {
      int albumId = albums.get(i).getId();
      List<Foto> fotoListe = fotoService.findAllByAlbumId(albumId);
      gallery.put(albums.get(i), fotoListe);  
    }
    model.addAttribute("gallery", gallery);
    
    return "albumslist";
    //return "startingpage";
  }

  /**
   * This method will provide the medium to add a new album.
   */
  @RequestMapping(value = { "/newalbum" }, method = RequestMethod.GET)
  public String newUser(ModelMap model) {
    Album album = new Album();
    model.addAttribute("album", album);
    model.addAttribute("edit", false);
    return "registration";
  }

  /**
   * This method will be called on form submission, handling POST request for
   * saving album in database. It also validates the album input
   */
  @RequestMapping(value = { "/newalbum" }, method = RequestMethod.POST)
  public String saveAlbum(
      @Valid Album album, BindingResult result,
      ModelMap model
  ) {

    if (result.hasErrors()) {
      return "registration";
    }

		/*
     * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
		 * and applying it on field [sso] of Model class [Album].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
    if (!albumService.isAlbumALBUMIDUnique(album.getId(), album.getAlbumId())) {
      FieldError ssoError = new FieldError("album",
          "albumId",
          messageSource.getMessage("non.unique.albumId", new String[] { album.getAlbumId() }, Locale.getDefault()));
      result.addError(ssoError);
      return "registration";
    }

    albumService.saveAlbum(album);

    model.addAttribute("album", album);
    model.addAttribute("success", "Album " + album.getFirstName() + " registered successfully");
    //return "success";
    return "registrationsuccess";
  }


  /**
   * This method will provide the medium to update an existing Album.
   */
  @RequestMapping(value = { "/edit-album-{albumId}" }, method = RequestMethod.GET)
  public String editAlbum(@PathVariable String albumId, ModelMap model) {
    Album album = albumService.findByALBUMID(albumId);
    model.addAttribute("album", album);
    model.addAttribute("edit", true);
    return "registration";
  }

  /**
   * This method will be called on form submission, handling POST request for
   * updating album in database. It also validates the album input
   */
  @RequestMapping(value = { "/edit-album-{albumId}" }, method = RequestMethod.POST)
  public String updateAlbum(
      @Valid Album album, BindingResult result,
      ModelMap model, @PathVariable String albumId
  ) {

    if (result.hasErrors()) {
      return "registration";
    }

    albumService.updateAlbum(album);

    model.addAttribute("success", "Album " + album.getFirstName() + " updated successfully");
    return "registrationsuccess";
  }


  /**
   * This method will delete an Album by it's ALBUMID value.
   */
  @RequestMapping(value = { "/delete-album-{albumId}" }, method = RequestMethod.GET)
  public String deleteAlbum(@PathVariable String albumId) {
    albumService.deleteAlbumByALBUMID(albumId);
    return "redirect:/list";
  }


  @RequestMapping(value = { "/add-foto-{albumId}" }, method = RequestMethod.GET)
  public String addFotos(@PathVariable int albumId, ModelMap model) {
    Album album = albumService.findById(albumId);
    model.addAttribute("album", album);

    FileBucket fileModel = new FileBucket();
    model.addAttribute("fileBucket", fileModel);

    List<Foto> fotos = fotoService.findAllByAlbumId(albumId);
    model.addAttribute("fotos", fotos);

    return "managefotos";
  }


  @RequestMapping(value = { "/download-foto-{albumId}-{fotoId}" }, method = RequestMethod.GET)
  public void downloadDocument(@PathVariable int albumId, @PathVariable int fotoId, HttpServletResponse response) throws
      IOException {
    Foto foto = fotoService.findById(fotoId);
    response.setContentType(foto.getType());
    response.setContentLength(foto.getContent().length);
    response.setHeader("Content-Disposition", "attachment; filename=\"" + foto.getName() + "\"");

    FileCopyUtils.copy(foto.getContent(), response.getOutputStream());
  }

  @RequestMapping(value = { "/delete-foto-{albumId}-{fotoId}" }, method = RequestMethod.GET)
  public String deleteDocument(@PathVariable int albumId, @PathVariable int fotoId) {
    fotoService.deleteById(fotoId);
    return "redirect:/add-foto-" + albumId;
  }

  @RequestMapping(value = { "/add-foto-{albumId}" }, method = RequestMethod.POST)
  public String uploadFoto(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable int albumId) throws
      IOException {

    if (result.hasErrors()) {
      System.out.println("validation errors");
      Album album = albumService.findById(albumId);
      model.addAttribute("album", album);

      List<Foto> fotos = fotoService.findAllByAlbumId(albumId);
      model.addAttribute("fotos", fotos);

      return "managefotos";
    } else {

      System.out.println("Fetching file");

      Album album = albumService.findById(albumId);
      model.addAttribute("album", album);

      saveFoto(fileBucket, album);

      return "redirect:/add-foto-" + albumId;
    }
  }

  private void saveFoto(FileBucket fileBucket, Album album) throws IOException {

    Foto foto = new Foto();

    MultipartFile multipartFile = fileBucket.getFile();

    foto.setName(multipartFile.getOriginalFilename());
    foto.setDescription(fileBucket.getDescription());
    foto.setType(multipartFile.getContentType());
    foto.setContent(multipartFile.getBytes());
    foto.setAlbum(album);
    fotoService.saveFoto(foto);
  }

  @RequestMapping(value = { "/fotoDisplay" }, method = RequestMethod.GET)
    public void showImage(@RequestParam("id") Integer fotoId, HttpServletResponse response,HttpServletRequest request) 
            throws ServletException, IOException{

      Foto foto = fotoService.findById(fotoId);        
      response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
      response.getOutputStream().write(foto.getContent());

      response.getOutputStream().close();
  	}

}

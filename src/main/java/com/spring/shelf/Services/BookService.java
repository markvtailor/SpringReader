package com.spring.shelf.Services;

import com.spring.shelf.Beans.S3Emulator;
import com.spring.shelf.Entities.BookEntity;
import com.spring.shelf.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ShelfService shelfService;
    @Autowired
    S3Emulator emulator;
    @Autowired
    BookConversionService convertor;

    public BookEntity uploading(String name, String author, String description, String owner, String shelfName, MultipartFile file) throws IOException {
        convertor.sendToConversion(file);
        BookEntity book = new BookEntity();
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setUserOwner(owner);
        book.setInternalName(owner+"_"+name);
        book.setShelf(shelfService.getShelf(owner,shelfName));
        return bookRepository.save(book);
    }

    public Set<BookEntity> getBook(String username, String name){
        return bookRepository.findByUserOwnerAndNameContains(username, name);
    }

    private boolean checkBucketExistence (String bucketName, S3Client s3Client){
        HeadBucketRequest headBucketRequest = HeadBucketRequest.builder()
                .bucket(bucketName)
                .build();

        try {
            s3Client.headBucket(headBucketRequest);
            return true;
        } catch (NoSuchBucketException e) {
            return false;
        }
    }
}

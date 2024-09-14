package fr.karam.data.entity.document;

import org.bson.BsonBinaryReader;
import org.bson.BsonBinaryWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.io.BasicOutputBuffer;

import java.nio.ByteBuffer;

public final class DocumentByteSerializer {

    private static final Codec<Document> DOCUMENT_CODEC = new DocumentCodec();

    public static byte[] serialize(EntityDocument entityDocument) {
        final Document document = entityDocument.asDocument();
        final BasicOutputBuffer outputBuffer = new BasicOutputBuffer();
        final BsonBinaryWriter writer = new BsonBinaryWriter(outputBuffer);

        DOCUMENT_CODEC.encode(writer, document, EncoderContext.builder().isEncodingCollectibleDocument(true).build());

        return outputBuffer.toByteArray();
    }

    public static EntityDocument deserialize(byte[] bytes) {
        final BsonBinaryReader bsonReader = new BsonBinaryReader(ByteBuffer.wrap(bytes));

        Document document = DOCUMENT_CODEC.decode(bsonReader, DecoderContext.builder().build());
        return EntityDocument.fromDocument(document);
    }

}

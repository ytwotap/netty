package com.yt.nettyhandler.proto;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

public interface PersonOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Person)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *required必须赋值，是永久性的,用户名字，可以重复
   * </pre>
   *
   * <code>required string name = 1;</code>
   * @return Whether the name field is set.
   */
  boolean hasName();
  /**
   * <pre>
   *required必须赋值，是永久性的,用户名字，可以重复
   * </pre>
   *
   * <code>required string name = 1;</code>
   * @return The name.
   */
  String getName();
  /**
   * <pre>
   *required必须赋值，是永久性的,用户名字，可以重复
   * </pre>
   *
   * <code>required string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   *唯一连接id。
   * </pre>
   *
   * <code>required int32 uuid = 2;</code>
   * @return Whether the uuid field is set.
   */
  boolean hasUuid();
  /**
   * <pre>
   *唯一连接id。
   * </pre>
   *
   * <code>required int32 uuid = 2;</code>
   * @return The uuid.
   */
  int getUuid();

  /**
   * <pre>
   *相互之间,交流的消息,目前只是简单的string
   * </pre>
   *
   * <code>optional string message = 3;</code>
   * @return Whether the message field is set.
   */
  boolean hasMessage();
  /**
   * <pre>
   *相互之间,交流的消息,目前只是简单的string
   * </pre>
   *
   * <code>optional string message = 3;</code>
   * @return The message.
   */
  String getMessage();
  /**
   * <pre>
   *相互之间,交流的消息,目前只是简单的string
   * </pre>
   *
   * <code>optional string message = 3;</code>
   * @return The bytes for message.
   */
  com.google.protobuf.ByteString
      getMessageBytes();
}
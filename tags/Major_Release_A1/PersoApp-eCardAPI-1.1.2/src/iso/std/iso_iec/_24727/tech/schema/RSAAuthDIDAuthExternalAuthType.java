
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RSAAuthDIDAuthExternalAuthType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RSAAuthDIDAuthExternalAuthType">
 *   &lt;complexContent>
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationDataType">
 *       &lt;sequence>
 *         &lt;element name="MutualCryptogram" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RSAAuthDIDAuthExternalAuthType")
public class RSAAuthDIDAuthExternalAuthType
    extends DIDAuthenticationDataType
{


}

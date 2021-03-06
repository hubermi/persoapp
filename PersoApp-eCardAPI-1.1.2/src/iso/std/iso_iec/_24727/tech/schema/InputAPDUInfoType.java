
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for InputAPDUInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InputAPDUInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InputAPDU" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *         &lt;element name="AcceptableStatusCode" type="{http://www.w3.org/2001/XMLSchema}hexBinary" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InputAPDUInfoType", propOrder = {
    "inputAPDU",
    "acceptableStatusCode"
})
public class InputAPDUInfoType {

    @XmlElement(name = "InputAPDU", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] inputAPDU;
    @XmlElement(name = "AcceptableStatusCode", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected List<byte[]> acceptableStatusCode;

    /**
     * Gets the value of the inputAPDU property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getInputAPDU() {
        return inputAPDU;
    }

    /**
     * Sets the value of the inputAPDU property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputAPDU(byte[] value) {
        this.inputAPDU = ((byte[]) value);
    }

    /**
     * Gets the value of the acceptableStatusCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the acceptableStatusCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAcceptableStatusCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<byte[]> getAcceptableStatusCode() {
        if (acceptableStatusCode == null) {
            acceptableStatusCode = new ArrayList<byte[]>();
        }
        return this.acceptableStatusCode;
    }

}
